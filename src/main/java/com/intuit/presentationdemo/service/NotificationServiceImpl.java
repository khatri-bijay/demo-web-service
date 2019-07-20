package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.common.event.AppointmentEvent;
import com.intuit.presentationdemo.service.contract.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void publishNotification(AppointmentEvent event) {
        log.info("Notification sent for {}", event);
    }
}
