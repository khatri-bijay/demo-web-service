package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.AppointmentStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppointmentStatusRepository extends CrudRepository<AppointmentStatus,Long> {
    Optional<AppointmentStatus> findByName(String name);

}
