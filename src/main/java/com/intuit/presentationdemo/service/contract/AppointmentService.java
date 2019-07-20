package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;

public interface AppointmentService {
    AppointmentQuery scheduleAppointment(long petId, AppointmentCommand command);
    void cancelAppointment(long appointmentId);
}
