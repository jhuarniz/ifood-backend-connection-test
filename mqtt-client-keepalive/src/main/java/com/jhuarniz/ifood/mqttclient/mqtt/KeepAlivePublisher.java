package com.jhuarniz.ifood.mqttclient.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway(defaultRequestChannel = "mqttKeepAliveOutboundChannel")
public interface KeepAlivePublisher {
    void publish(String data);
}
