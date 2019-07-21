package com.intuit.presentationdemo.dto.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentCommand {
    private Date date;
    private VetCommand vet;
    private String start;
    private String end;
}
