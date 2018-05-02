package com.jhuarniz.ifood.mqttclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MqttClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MqttClientApplication.class, args);

        context.getBean(ScheduleService.class).schedule();
    }


}
