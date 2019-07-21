package com.intuit.presentationdemo.common.util;

import com.intuit.presentationdemo.domain.*;
import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.command.VetCommand;
import com.intuit.presentationdemo.dto.query.*;
import org.modelmapper.ModelMapper;

public final class ModelMapperUtil {
    private ModelMapperUtil() {}
    private static ModelMapper modelMapper = new ModelMapper();
    public static Pet toPet(final PetCommand petCommand){
        Pet pet = new Pet();
        pet.setName(petCommand.getName());
        pet.setType(toPetType(petCommand.getType()));
        return pet;
    }

    public static PetQuery toPetQuery(final Pet pet) {
        return modelMapper.map(pet, PetQuery.class);
    }

    public static PetTypeQuery toPetTypeQuery(final PetType petType) {
        return modelMapper.map(petType, PetTypeQuery.class);
    }

    public static PetType toPetType(final PetCommand.PetTypeCommand command) {
        PetType petType = new PetType();
        if(command != null) {
            petType.setName(command.getName());
        }
        return petType;
    }

    public static Vet toVet(final VetCommand command){
        Vet vet = new Vet();
        vet.setName(command.getName());
        vet.setSpecialty(toSpecialty(command.getSpecialty()));
        return vet;
    }

    public static VetQuery toVetQuery(final Vet vet) {
       return modelMapper.map(vet, VetQuery.class);
    }

    public static SpecialtyQuery toSpecialtyQuery(final Specialty specialty) {
        return modelMapper.map(specialty, SpecialtyQuery.class);
    }
    public static Specialty toSpecialty(final VetCommand.SpecialtyCommand command) {
        Specialty specialty = new Specialty();
        if(command != null) {
            specialty.setName(command.getName());
        }
        return specialty;
    }

    public static AppointmentStatusQuery toAppointmentStatusQuery(final AppointmentStatus status){
        return modelMapper.map(status, AppointmentStatusQuery.class);
    }
    public static AppointmentQuery toAppointmentQuery(final Appointment appointment){
        AppointmentQuery query = new AppointmentQuery();
        query.setAppointmentStatus(toAppointmentStatusQuery(appointment.getAppointmentStatus()));
        query.setId(appointment.getId());
        query.setDate(appointment.getDate());
        query.setPet(toPetQuery(appointment.getPet()));
        VetQuery vetQuery = new VetQuery();
        vetQuery.setId(appointment.getVet().getId());
        vetQuery.setName(appointment.getVet().getName());
        query.setVet(vetQuery);
        return query;
    }

}
