package com.jhuarniz.ifood.mqttclient;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.jhuarniz.ifood.mqttclient.mqtt.SchedulePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ScheduleService {

    SchedulePublisher schedulePublisher;


    @Autowired
    public ScheduleService(SchedulePublisher schedulePublisher) throws InterruptedException {
        this.schedulePublisher = schedulePublisher;
    }

    public void schedule() {
        try{
//            ObjectToJsonTransformer objectToJsonTransformer = new ObjectToJsonTransformer();
//            String what = (String) objectToJsonTransformer.transform(new GenericMessage<Request>(new Request(new Date(),new Date()))).getPayload();

            ObjectMapper mapper = new ObjectMapper();
//            String what = mapper.writeValueAsString(new Request(LocalDateTime.now(),LocalDateTime.now()));

            Date begin = new Date();
            begin.setTime(begin.getTime()+ (10*1000));
            Date end = new Date();
            end.setTime(begin.getTime() + (30*1000) );
            String what = mapper.writeValueAsString(new Request(begin, end));

            schedulePublisher.publish(what);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    class Request{

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Date begin;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Date end;

        public Request(Date begin, Date end) {
            this.begin = begin;
            this.end = end;
        }

        public Date getBegin() {
            return begin;
        }

        public void setBegin(Date begin) {
            this.begin = begin;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }
    }

}
