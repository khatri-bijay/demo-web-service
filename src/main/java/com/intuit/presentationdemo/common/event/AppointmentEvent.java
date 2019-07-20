package com.intuit.presentationdemo.common.event;

import com.intuit.presentationdemo.common.ApiConstant;
import org.springframework.context.ApplicationEvent;

public abstract class AppointmentEvent extends ApplicationEvent {
    protected ApiConstant.APPOINTMENT_STATUS appointment_status;
    public AppointmentEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "AppointmentEvent{" +
                "appointment_status=" + appointment_status +
                ", source=" + source +
                '}';
    }
}
