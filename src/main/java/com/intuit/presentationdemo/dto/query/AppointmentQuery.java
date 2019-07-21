package com.intuit.presentationdemo.dto.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
public class AppointmentQuery {
    private long id;
    private Date date;
    private VetQuery vet;
    private PetQuery pet;
    private String start;
    private String end;
    private AppointmentStatusQuery appointmentStatus;
}
