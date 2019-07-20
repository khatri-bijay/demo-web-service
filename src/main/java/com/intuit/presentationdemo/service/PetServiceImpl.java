package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.util.ModelMapperUtil;
import com.intuit.presentationdemo.domain.Pet;
import com.intuit.presentationdemo.domain.PetType;
import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.query.PetQuery;
import com.intuit.presentationdemo.dto.query.PetTypeQuery;
import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.repository.PetRepository;
import com.intuit.presentationdemo.repository.PetTypeRepository;
import com.intuit.presentationdemo.service.contract.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PetServiceImpl implements PetService {
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;

    public PetServiceImpl(PetTypeRepository petTypeRepository, PetRepository petRepository) {
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Set<PetTypeQuery> getPetTypes() {
        Iterable<PetType> petTypes = petTypeRepository.findAll();
        if(petTypes == null) {
            return Collections.emptySet();
        }

        return Collections.unmodifiableSet(
                StreamSupport.stream(petTypes.spliterator(), true)
                        .map(ModelMapperUtil::toPetTypeQuery)
                .collect(Collectors.toSet()));
    }

    @Override
    public PetQuery addPet(PetCommand petCommand) {
        String type = petCommand.getType().getName(); // TODO: Discussion required
        Optional<PetType> petType = petTypeRepository.findByNameIgnoreCase(type);
        if(!petType.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ApiConstant.CLIENT_INPUT_ERROR,ApiConstant.CLIENT_INPUT_ERROR_MESSAGE);
        }
        Pet pet = ModelMapperUtil.toPet(petCommand);
        pet.setType(petType.get());
        return ModelMapperUtil.toPetQuery(petRepository.save(pet));
    }
}
