package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.Appointment;
import com.intuit.presentationdemo.domain.AppointmentStatus;
import com.intuit.presentationdemo.domain.Pet;
import com.intuit.presentationdemo.domain.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Set<Appointment>> findByVetAndDateAndAppointmentStatus(Vet pet, Date date, AppointmentStatus status);
}
