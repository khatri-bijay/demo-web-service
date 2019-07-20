package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.PetType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    Optional<PetType> findByNameIgnoreCase(String name);
}
