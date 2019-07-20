package com.intuit.presentationdemo.config;

import com.intuit.presentationdemo.domain.AppointmentStatus;
import com.intuit.presentationdemo.domain.PetType;
import com.intuit.presentationdemo.domain.Specialty;
import com.intuit.presentationdemo.repository.AppointmentStatusRepository;
import com.intuit.presentationdemo.repository.PetTypeRepository;
import com.intuit.presentationdemo.repository.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"dev", "default"})
@Configuration
@Slf4j
public class DbSeeder implements CommandLineRunner {
    private final PetTypeRepository petTypeRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final SpecialtyRepository specialtyRepository;

    public DbSeeder(PetTypeRepository petTypeRepository, AppointmentStatusRepository appointmentStatusRepository, SpecialtyRepository specialtyRepository) {
        this.petTypeRepository = petTypeRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Database seeding in progress");
        seedPetType();
        seedAppointmentStatus();
        seedSpecialties();
    }

    private void seedAppointmentStatus() {
        if(!appointmentStatusRepository.findByName("BOOKED").isPresent()){
            AppointmentStatus booked = new AppointmentStatus();
            booked.setName("BOOKED");
            appointmentStatusRepository.save(booked);
        }

        if(!appointmentStatusRepository.findByName("CANCELLED").isPresent()){
            AppointmentStatus cancelled = new AppointmentStatus();
            cancelled.setName("CANCELLED");
            appointmentStatusRepository.save(cancelled);
        }

        if(!appointmentStatusRepository.findByName("COMPLETED").isPresent()){
            AppointmentStatus completed = new AppointmentStatus();
            completed.setName("COMPLETED");
            appointmentStatusRepository.save(completed);
        }
    }

    private void seedSpecialties() {
        if(!specialtyRepository.findByNameIgnoreCase("DOG").isPresent()){
            Specialty dog = new Specialty();
            dog.setName("DOG");
            specialtyRepository.save(dog);
        }

        if(!specialtyRepository.findByNameIgnoreCase("CAT").isPresent()){
            Specialty cat = new Specialty();
            cat.setName("CAT");
            specialtyRepository.save(cat);
        }

        if(!specialtyRepository.findByNameIgnoreCase("BIRD").isPresent()){
            Specialty bird = new Specialty();
            bird.setName("BIRD");
            specialtyRepository.save(bird);
        }
    }

    private void seedPetType() {
        if(!petTypeRepository.findByNameIgnoreCase("DOG").isPresent()){
            PetType dog = new PetType();
            dog.setName("DOG");
            petTypeRepository.save(dog);
        }

        if(!petTypeRepository.findByNameIgnoreCase("CAT").isPresent()){
            PetType dog = new PetType();
            dog.setName("CAT");
            petTypeRepository.save(dog);
        }

        if(!petTypeRepository.findByNameIgnoreCase("BIRD").isPresent()){
            PetType dog = new PetType();
            dog.setName("BIRD");
            petTypeRepository.save(dog);
        }
    }
}
