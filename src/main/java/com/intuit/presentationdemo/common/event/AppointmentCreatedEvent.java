package com.intuit.presentationdemo.common.event;

import com.intuit.presentationdemo.common.ApiConstant;

public class AppointmentCreatedEvent extends AppointmentEvent{
    public AppointmentCreatedEvent(Object source) {
        super(source);
        this.appointment_status = ApiConstant.APPOINTMENT_STATUS.BOOKED;
    }
}
