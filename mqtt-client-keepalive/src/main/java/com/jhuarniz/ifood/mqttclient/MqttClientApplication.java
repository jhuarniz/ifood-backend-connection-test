package com.jhuarniz.ifood.mqttclient;

import com.jhuarniz.ifood.mqttclient.mqtt.KeepAlivePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MqttClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MqttClientApplication.class, args);
        context.getBean(KeepaliveService.class).start();
    }


}
