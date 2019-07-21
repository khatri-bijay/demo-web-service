package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    AppointmentQuery scheduleAppointment(long petId, AppointmentCommand command);
    void cancelAppointment(long appointmentId);
    List<AppointmentQuery> getAppointments(long vetId, Date date);
}
