package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.common.util.ModelMapperUtil;
import com.intuit.presentationdemo.domain.Pet;
import com.intuit.presentationdemo.domain.PetType;
import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.query.PetQuery;
import com.intuit.presentationdemo.dto.query.PetTypeQuery;
import com.intuit.presentationdemo.repository.PetRepository;
import com.intuit.presentationdemo.repository.PetTypeRepository;
import com.intuit.presentationdemo.service.contract.PetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PetServiceTest {
    private PetTypeRepository mockPetTypeRepository;
    private PetRepository mockPetRepository;
    private Pet expectedPet;
    private PetCommand expectedCommand;
    private Set<PetType> expectedPetTypes;

    private PetService sut;

    @Before
    public void setup(){
        mockPetRepository = mock(PetRepository.class);
        mockPetTypeRepository = mock(PetTypeRepository.class);
        sut = new PetServiceImpl(mockPetTypeRepository, mockPetRepository);

        PetType expectedPetType = new PetType();
        expectedPetType.setId(1);
        expectedPetType.setName("EXPECTED");

        PetType expectedPetType2 = new PetType();
        expectedPetType2.setId(2);
        expectedPetType2.setName("EXPECTED2");

        expectedPet = new Pet();
        expectedPet.setName("Expected pet");
        expectedPet.setId(124);
        expectedPet.setType(expectedPetType);

        expectedCommand = new PetCommand();
        expectedCommand.setName("Any Name");
        expectedCommand.setType(new PetCommand.PetTypeCommand("Any Thing"));

        expectedPetTypes = new HashSet<>();
        expectedPetTypes.add(expectedPetType);
        expectedPetTypes.add(expectedPetType2);

        Mockito.when(mockPetTypeRepository.findByNameIgnoreCase(anyString()))
                .thenReturn(Optional.of(expectedPetType));

        Mockito.when(mockPetRepository.save(any(Pet.class)))
                .thenReturn(expectedPet);

    }


    @Test
    public void givenPetTypeIsInvalid_throwsException(){
        Mockito.when(mockPetTypeRepository.findByNameIgnoreCase(anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ApiException.class, () -> sut.addPet(expectedCommand));
    }

    @Test
    public void givenCommandIsValid_whenPetIsAdded_callsRepository() {
        ArgumentCaptor<Pet> petArgumentCaptor = ArgumentCaptor.forClass(Pet.class);
        sut.addPet(expectedCommand);

        verify(mockPetRepository, times(1)).save(petArgumentCaptor.capture());

    }
    @Test
    public void givenCommandIsValid_whenPetIsAdded_returnsSavedPet() {
        PetQuery expected = ModelMapperUtil.toPetQuery(expectedPet);
        PetQuery actual = sut.addPet(expectedCommand);
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());

    }

    @Test
    public void givenPetTypesAreEmpty_whenGetAllIsCalled_returnsEmptyList(){
        Mockito.when(mockPetTypeRepository.findAll())
                .thenReturn(null);

        Set<PetTypeQuery> actual = sut.getPetTypes();
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void givenPetTypesAreValid_whenGetAllIsCalled_returnsAll(){
        Mockito.when(mockPetTypeRepository.findAll())
                .thenReturn(expectedPetTypes);

        Set<PetTypeQuery> expected = expectedPetTypes.stream()
                .map(ModelMapperUtil::toPetTypeQuery)
                .collect(Collectors.toSet());

        Set<PetTypeQuery> actual = sut.getPetTypes();
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(actual.parallelStream().allMatch(p -> p.getId() > 0));
    }



}
