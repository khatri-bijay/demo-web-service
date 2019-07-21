package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    AppointmentQuery scheduleAppointment(long petId, AppointmentCommand command);
    void cancelAppointment(long appointmentId);
    List<AppointmentQuery> getAppointmentsForVet(long vetId, Date date);
    List<AppointmentQuery> getAppointmentsForVet(long vetId);
    List<AppointmentQuery> getAppointmentsForPet(long petId, Date date);
    List<AppointmentQuery> getAppointmentsForPet(long petId);
}
