package com.intuit.presentationdemo.common.validator;

import com.intuit.presentationdemo.dto.command.AppointmentCommand;

import java.util.Date;

public class AppointmentValidator{
    public static boolean isInvalid(AppointmentCommand command) {
        return command.getDate() == null || command.getVet() == null || new Date().after(command.getDate());
    }
}
