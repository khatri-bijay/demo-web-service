package com.intuit.presentationdemo.common.event;

import com.intuit.presentationdemo.common.ApiConstant;

public class AppointmentCancelledEvent extends AppointmentEvent {

    public AppointmentCancelledEvent(Object source) {
        super(source);
        this.appointment_status = ApiConstant.APPOINTMENT_STATUS.CANCELLED;
    }

}
