package com.jhuarniz.ifood.mqttclient;


import com.jhuarniz.ifood.mqttclient.mqtt.KeepAlivePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class KeepaliveService {

    KeepAlivePublisher keepAlivePublisher;

    @Autowired
    public KeepaliveService(KeepAlivePublisher keepAlivePublisher) throws InterruptedException {
        this.keepAlivePublisher = keepAlivePublisher;
    }

    boolean run = false;

    public void start() {
        run = true;
        while(run){
            try{
                keepAlivePublisher.publish("beat");
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.run = false;
    }
}
