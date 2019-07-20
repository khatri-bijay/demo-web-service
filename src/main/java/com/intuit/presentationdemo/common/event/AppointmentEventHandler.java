package com.intuit.presentationdemo.common.event;

import com.intuit.presentationdemo.service.contract.NotificationService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentEventHandler implements ApplicationListener<AppointmentEvent> {
    private final NotificationService notificationService;

    public AppointmentEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(AppointmentEvent event) {
        notificationService.publishNotification(event);
    }
}
