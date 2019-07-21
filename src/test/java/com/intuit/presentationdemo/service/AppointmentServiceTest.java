package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.TestUtils;
import com.intuit.presentationdemo.common.event.AppointmentEventPublisher;
import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.domain.Vet;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;
import com.intuit.presentationdemo.repository.AppointmentRepository;
import com.intuit.presentationdemo.repository.AppointmentStatusRepository;
import com.intuit.presentationdemo.repository.PetRepository;
import com.intuit.presentationdemo.repository.VetRepository;
import com.intuit.presentationdemo.service.contract.AppointmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {
    private AppointmentRepository mockAppointmentRepository;
    private AppointmentStatusRepository mockAppointmentStatusRepository;
    private VetRepository mockVetRepository;
    private PetRepository mockPetRepository;
    private AppointmentEventPublisher mockAppointmentEventPublisher;

    private AppointmentService sut;
    @Before
    public void setup() {
        mockAppointmentEventPublisher = mock(AppointmentEventPublisher.class);
        mockAppointmentRepository = mock(AppointmentRepository.class);
        mockAppointmentStatusRepository = mock(AppointmentStatusRepository.class);
        mockVetRepository = mock(VetRepository.class);
        mockVetRepository = mock(VetRepository.class);
        mockPetRepository = mock(PetRepository.class);

        sut = new AppointmentServiceImpl(mockAppointmentRepository, mockAppointmentStatusRepository,
                mockPetRepository, mockVetRepository, mockAppointmentEventPublisher);

        when(mockVetRepository.findById(anyLong()))
                .thenReturn(Optional.of(TestUtils.expectedVet()));
    }

    @Test
    public void givenVetIsInvalid_throwsNotFoundException() {

        when(mockVetRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ApiException.class, () -> sut.getAppointments(0, new Date()));
    }

    @Test
    public void givenVetIsValid_returnsEmptyListForGetAppointments() {
        List<AppointmentQuery> actual = sut.getAppointments(1, new Date());

        Assertions.assertEquals(Collections.emptyList(), actual);

    }

    @Test
    public void givenVetIsValid_returnsListForGetAppointments() {
        when(mockAppointmentRepository.findByVetAndDate(any(Vet.class), any(Date.class)))
                .thenReturn(Optional.of(TestUtils.expectedAppointmentSet()));

        List<AppointmentQuery> actual = sut.getAppointments(1, new Date());
        Assertions.assertEquals(TestUtils.expectedAppointmentSet().size(), actual.size());

    }


}
