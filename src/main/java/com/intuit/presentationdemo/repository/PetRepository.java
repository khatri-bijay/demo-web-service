package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PetRepository extends CrudRepository<Pet, Long> {
    @Override
    Set<Pet> findAll();
}
