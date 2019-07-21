package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.event.AppointmentCancelledEvent;
import com.intuit.presentationdemo.common.event.AppointmentCreatedEvent;
import com.intuit.presentationdemo.common.event.AppointmentEventPublisher;
import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.common.util.ModelMapperUtil;
import com.intuit.presentationdemo.domain.Appointment;
import com.intuit.presentationdemo.domain.AppointmentStatus;
import com.intuit.presentationdemo.domain.Pet;
import com.intuit.presentationdemo.domain.Vet;
import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;
import com.intuit.presentationdemo.repository.AppointmentRepository;
import com.intuit.presentationdemo.repository.AppointmentStatusRepository;
import com.intuit.presentationdemo.repository.PetRepository;
import com.intuit.presentationdemo.repository.VetRepository;
import com.intuit.presentationdemo.service.contract.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private static final String CLASS_NAME = AppointmentServiceImpl.class.getName();

    private static AppointmentStatus BOOKED;
    private static AppointmentStatus CANCELLED;
    private static AppointmentStatus COMPLETED;

    private final AppointmentRepository appointmentRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final AppointmentEventPublisher eventPublisher;


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentStatusRepository appointmentStatusRepository,
                                  PetRepository petRepository, VetRepository vetRepository, AppointmentEventPublisher eventPublisher) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.eventPublisher = eventPublisher;
    }


    public void storeStatus() {
        if(BOOKED == null)
            BOOKED = appointmentStatusRepository.findByName("BOOKED").get();
        if(CANCELLED == null)
            CANCELLED = appointmentStatusRepository.findByName("CANCELLED").get();
        if(COMPLETED == null)
            COMPLETED = appointmentStatusRepository.findByName("COMPLETED").get();
    }

    @Override
    public AppointmentQuery scheduleAppointment(long petId, AppointmentCommand command) {
        Appointment appointment = new Appointment();
        Optional<Pet> pet = petRepository.findById(petId);
        assertIsValid(pet, "Pet");
        Optional<Vet> vet = vetRepository.findById(command.getVet().getId());
        assertIsValid(vet, "Vet");
        checkConflict(vet.get(), command.getDate());
        appointment.setDate(command.getDate());
        appointment.setAppointmentStatus(BOOKED);
        appointment.setPet(pet.get());
        appointment.setVet(vet.get());
        appointment.setStart(command.getStart());
        appointment.setEnd(command.getEnd());

        Appointment newAppointment = appointmentRepository.save(appointment);
        eventPublisher.publish(new AppointmentCreatedEvent(appointment)); //TODO: Can be made non-blocking ?
        return ModelMapperUtil.toAppointmentQuery(newAppointment);
    }

    private void checkConflict(Vet vet, Date date) {
        storeStatus();

        if(appointmentRepository.findByVetAndDateAndAppointmentStatus(vet, date, BOOKED)
                .isPresent()) {
            throw new ApiException.Builder("Error: Conflict, Vet already has an existing appointment for " + date)
                    .errorCode(ApiConstant.CONFLICT)
                    .className(CLASS_NAME)
                    .statusCode(HttpStatus.CONFLICT)
                    .build();
        }
    }

    private <T> void assertIsValid(Optional<T> resource, String type) {
        if(!resource.isPresent()) {
            throw new ApiException.Builder(type + " not found")
                    .method("assertPetIsValid")
                    .className(CLASS_NAME)
                    .errorCode(ApiConstant.RESOURCE_NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    public void cancelAppointment(long appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if(!appointment.isPresent()) {
            throw new ApiException.Builder("Error: Appointment not found.")
                    .className(CLASS_NAME)
                    .method("cancelAppointment")
                    .errorCode(ApiConstant.RESOURCE_NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
        }
        appointmentRepository.delete(appointment.get());
        eventPublisher.publish(new AppointmentCancelledEvent(appointment));
    }

    @Override
    public List<AppointmentQuery> getAppointmentsForVet(long vetId, Date date) {
        Optional<Vet> vet = assertVetIsValid(vetId);
        Optional<Set<Appointment>> appointments = appointmentRepository.findByVetAndDate(vet.get(), date);
        return buildAppointmentQueries(appointments);
    }

    private List<AppointmentQuery> buildAppointmentQueries(Optional<Set<Appointment>> appointments) {
        return appointments.map(appointmentSet -> Collections.unmodifiableList(appointmentSet.parallelStream()
                .map(ModelMapperUtil::toAppointmentQuery)
                .collect(Collectors.toList())))
                .orElse(Collections.emptyList());
    }

    private Optional<Vet> assertVetIsValid(long vetId) {
        Optional<Vet> vet = vetRepository.findById(vetId);
        if(!vet.isPresent()) {
            throw new ApiException.Builder("Error: Vet not found.")
                    .className(CLASS_NAME)
                    .method("getAppointmentsForVet")
                    .errorCode(ApiConstant.RESOURCE_NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
        }
        return vet;
    }

    @Override
    public List<AppointmentQuery> getAppointmentsForVet(long vetId) {
        Optional<Vet> vet = assertVetIsValid(vetId);
        Optional<Set<Appointment>> appointments = appointmentRepository.findByVet(vet.get());
        return buildAppointmentQueries(appointments);
    }

    @Override
    public List<AppointmentQuery> getAppointmentsForPet(long petId, Date date) {
        Optional<Pet> pet = assertPetIsValid(petId);
        Optional<Set<Appointment>> appointments = appointmentRepository.findByPetAndDate(pet.get(), date);
        return buildAppointmentQueries(appointments);
    }

    private Optional<Pet> assertPetIsValid(long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if(!pet.isPresent()) {
            throw new ApiException.Builder("Error: Pet not found.")
                    .className(CLASS_NAME)
                    .method("getAppointmentsForPet")
                    .errorCode(ApiConstant.RESOURCE_NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND)
                    .build();
        }
        return pet;
    }

    @Override
    public List<AppointmentQuery> getAppointmentsForPet(long petId) {
        Optional<Pet> pet = assertPetIsValid(petId);
        Optional<Set<Appointment>> appointments = appointmentRepository.findByPet(pet.get());
        return buildAppointmentQueries(appointments);
    }

}
