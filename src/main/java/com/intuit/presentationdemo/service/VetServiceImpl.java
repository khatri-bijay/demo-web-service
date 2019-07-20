package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.common.util.ModelMapperUtil;
import com.intuit.presentationdemo.domain.Specialty;
import com.intuit.presentationdemo.domain.Vet;
import com.intuit.presentationdemo.dto.command.VetCommand;
import com.intuit.presentationdemo.dto.query.VetQuery;
import com.intuit.presentationdemo.repository.SpecialtyRepository;
import com.intuit.presentationdemo.repository.VetRepository;
import com.intuit.presentationdemo.service.contract.VetService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VetServiceImpl implements VetService {
    private final VetRepository vetRepository;
    private final SpecialtyRepository specialtyRepository;

    public VetServiceImpl(VetRepository vetRepository, SpecialtyRepository specialtyRepository) {
        this.vetRepository = vetRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> getSpecialties() {
        Iterable<Specialty> specialties = specialtyRepository.findAll();
        if(specialties == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(
                StreamSupport.stream(specialties.spliterator(), true)
                .collect(Collectors.toSet()));
    }

    @Override
    public VetQuery addVet(VetCommand vetCommand) {
        Specialty specialty = verifySpecialty(vetCommand.getSpecialty());
        Vet vet = ModelMapperUtil.toVet(vetCommand);
        vet.setSpecialty(specialty);
        Vet newVet = vetRepository.save(vet);
        return ModelMapperUtil.toVetQuery(newVet);
    }

    @Override
    public Set<VetQuery> findAll() {
        Iterable<Vet> vets = vetRepository.findAll();
        return buildVetQueries(vets);

    }

    private Set<VetQuery> buildVetQueries(Iterable<Vet> vets) {
        return Collections.unmodifiableSet(StreamSupport.stream(vets.spliterator(), true) // Collection mapper ?
                .map(ModelMapperUtil::toVetQuery)
                .collect(Collectors.toSet()));
    }

    @Override
    public Set<VetQuery> findAllByType(String specialty) {
        Optional<Set<Vet>> vets = vetRepository.findBySpecialty(specialty);
        if(vets.isPresent()) {
            return Collections.emptySet();
        }
        return buildVetQueries(vets.get());


    }

    private Specialty verifySpecialty(VetCommand.SpecialtyCommand s) {
        final Optional<Specialty> specialty = specialtyRepository.findByNameIgnoreCase(s.getName());
        if(!specialty.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ApiConstant.CLIENT_INPUT_ERROR,ApiConstant.CLIENT_INPUT_SPECIALTY_MESSAGE);
            //TODO: One could argue, this is a internal server exception ??
        }
        return specialty.get();
    }
}
