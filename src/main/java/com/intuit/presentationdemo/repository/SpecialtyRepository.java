package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.Specialty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
    Optional<Specialty> findByNameIgnoreCase(String name);
}
