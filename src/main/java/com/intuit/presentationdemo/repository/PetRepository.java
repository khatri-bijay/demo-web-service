package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
