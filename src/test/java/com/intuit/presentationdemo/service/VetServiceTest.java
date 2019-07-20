package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.domain.Specialty;
import com.intuit.presentationdemo.domain.Vet;
import com.intuit.presentationdemo.dto.command.VetCommand;
import com.intuit.presentationdemo.dto.query.VetQuery;
import com.intuit.presentationdemo.repository.SpecialtyRepository;
import com.intuit.presentationdemo.repository.VetRepository;
import com.intuit.presentationdemo.service.contract.VetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class VetServiceTest {
    private VetRepository mockVetRepository;
    private SpecialtyRepository mockSpecialtyRepository;
    private Vet expectedVet;
    private VetCommand expectedCommand;

    private VetService sut;

    @Before
    public void setup() {
        mockVetRepository = mock(VetRepository.class);
        mockSpecialtyRepository = mock(SpecialtyRepository.class);
        sut = new VetServiceImpl(mockVetRepository, mockSpecialtyRepository);

        VetCommand.SpecialtyCommand sc1 = new VetCommand.SpecialtyCommand("Specialty1");
        VetCommand.SpecialtyCommand sc2 = new VetCommand.SpecialtyCommand("Specialty12");

        expectedCommand = new VetCommand();
        expectedCommand.setName("Vet1");
        expectedCommand.setSpecialty(sc1);

        Specialty s = new Specialty();
        s.setId(1);
        s.setName("ONE");

        expectedVet = new Vet();
        expectedVet.setId(111);
        expectedVet.setName("Name");
        expectedVet.setSpecialty(s);

        Mockito.when(mockSpecialtyRepository.findByNameIgnoreCase(anyString()))
                .thenReturn(Optional.of(s));

        Mockito.when(mockVetRepository.save(any(Vet.class)))
                .thenReturn(expectedVet);
    }

    @Test
    public void givenSpecialtyIsInvalid_throwsException(){
        Mockito.when(mockSpecialtyRepository.findByNameIgnoreCase(anyString()))
                .thenReturn(Optional.empty());
        expectedCommand.setSpecialty(new VetCommand.SpecialtyCommand("INVALID"));

        Assertions.assertThrows(ApiException.class, () -> sut.addVet(expectedCommand));
    }

    @Test
    public void givenCommandIsValid_whenVetIsAdded_callsRepository() {
        ArgumentCaptor<Vet> vetArgumentCaptor = ArgumentCaptor.forClass(Vet.class);
        sut.addVet(expectedCommand);

        verify(mockVetRepository, times(1)).save(vetArgumentCaptor.capture());
        Assertions.assertEquals(expectedCommand.getName(), vetArgumentCaptor.getValue().getName());
    }

    @Test
    public void givenCommandIsValid_whenVetIsAdded_returnsSavedVet() {
        VetQuery vetQuery = sut.addVet(expectedCommand);
        Assertions.assertNotNull(vetQuery);
        Assertions.assertTrue(vetQuery.getId() > 0);
    }

}
