package com.jhuarniz.ifood.mqttclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;


@Configuration
public class MqqtConfig {

    @Value("${mqtt.brokerlist}")
    String brokerlist;

    @Value("${mqtt.topic.schedule.name}")
    String topicScheduleName;

    @Value("${mqtt.topic.keepalive.name}")
    String topicKeepaliveName;

    @Value("${mqtt.clientId:default-client-keepalive-id}")
    String clientKeepaliveId;

    @Value("${mqtt.clientId:default-client-schedule-id}")
    String clientScheduleId;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs(brokerlist);
        return factory;
    }

    // KeepAlivePublish
    @Bean
    @ServiceActivator(inputChannel = "mqttKeepAliveOutboundChannel")
    public MessageHandler mqttKeepAliveOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(clientKeepaliveId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topicKeepaliveName);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttKeepAliveOutboundChannel() {
        return new DirectChannel();
    }

    // SchedulePublish

//    @Bean
//    @ServiceActivator(inputChannel = "mqttScheduleOutboundChannel")
//    public MessageHandler mqttScheduleOutbound() {
//        MqttPahoMessageHandler messageHandler =
//                new MqttPahoMessageHandler(clientScheduleId, mqttClientFactory());
//        messageHandler.setAsync(true);
//        messageHandler.setDefaultTopic(topicScheduleName);
//        return messageHandler;
//    }
//
//    @Bean
//    public MessageChannel mqttScheduleOutboundChannel() {
//        return new DirectChannel();
//    }


}
