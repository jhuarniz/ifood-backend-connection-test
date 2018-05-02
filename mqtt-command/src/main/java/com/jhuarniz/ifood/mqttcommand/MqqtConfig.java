package com.jhuarniz.ifood.mqttcommand;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
public class MqqtConfig {

    @Value("${mqtt.brokerlist}")
    String brokerlist;

    @Value("${mqtt.topic.schedule.name}")
    String topicScheduleName;

    @Value("${mqtt.topic.keepalive.name}")
    String topicKeepaliveName;

    @Value("${mqtt.clientId:default-keepalive-command-id}")
    String topicKeepaliveClientId;

    @Value("${mqtt.clientId:default-schedule-command-id}")
    String topicScheduleClientId;



    //Keepalive Subscriber
    @Bean
    public MessageChannel mqttKeepaliveInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inboundKeepalive() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(brokerlist, topicKeepaliveClientId,topicKeepaliveName);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttKeepaliveInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttKeepaliveInputChannel")
    public MessageHandler keepaliveHandler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("keepalive >> " + message.getPayload());
            }

        };
    }


    //Schedule Subscriber
    @Bean
    public MessageChannel mqttScheduleInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inboundSchedule() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(brokerlist, topicScheduleClientId,topicScheduleName);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttScheduleInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttScheduleInputChannel")
    public MessageHandler scheduleHandler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("schedule >> " + message.getPayload());
            }

        };
    }

}
