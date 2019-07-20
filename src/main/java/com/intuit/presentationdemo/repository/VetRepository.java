package com.intuit.presentationdemo.repository;

import com.intuit.presentationdemo.domain.Specialty;
import com.intuit.presentationdemo.domain.Vet;
import com.intuit.presentationdemo.dto.query.VetQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface VetRepository extends CrudRepository<Vet, Long> {
    @Query("select v from Vet v join fetch v.specialties s where s.name = :name")
    Optional<Set<Vet>> findAllBySpecialty(String name);
}
