package com.jhuarniz.ifood.mqttclient.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway(defaultRequestChannel = "mqttScheduleOutboundChannel")
public interface SchedulePublisher {
    void publish(String data);
}