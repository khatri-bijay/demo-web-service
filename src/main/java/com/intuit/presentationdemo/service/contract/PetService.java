package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.query.PetQuery;
import com.intuit.presentationdemo.dto.query.PetTypeQuery;

import java.util.Set;

public interface PetService {
    Set<PetTypeQuery> getPetTypes();
    PetQuery addPet(PetCommand petCommand);
    Set<PetQuery> getAllPets();
}
