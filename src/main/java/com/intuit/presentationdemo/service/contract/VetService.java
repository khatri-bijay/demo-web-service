package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.domain.Specialty;
import com.intuit.presentationdemo.dto.command.VetCommand;
import com.intuit.presentationdemo.dto.query.VetQuery;

import java.util.Set;


public interface VetService {
    Set<Specialty> getSpecialties();
    VetQuery addVet(VetCommand vetCommand);

    Set<VetQuery> findAll();
    Set<VetQuery> findAllByType(String specialty);
}
