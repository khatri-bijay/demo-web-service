package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
