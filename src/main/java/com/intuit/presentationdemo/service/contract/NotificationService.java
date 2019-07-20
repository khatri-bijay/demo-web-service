package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.common.event.AppointmentEvent;

public interface NotificationService {
    void publishNotification(AppointmentEvent event);
}
