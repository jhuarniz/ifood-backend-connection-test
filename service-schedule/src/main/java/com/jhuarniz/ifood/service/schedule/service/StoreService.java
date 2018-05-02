package com.jhuarniz.ifood.service.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Service
public class StoreService {

    private RestTemplate template;


    @Value("storeservice.url:http://localhost:8080")
    private String storeServiceURL;

    @Autowired
    public StoreService(RestTemplate template) {
        this.template = template;
    }

    public String createSchedule(CreateScheduleRequest createScheduleRequest){

        validation(createScheduleRequest);

        CreateScheduleResponse response = this.template.postForObject(storeServiceURL, createScheduleRequest, CreateScheduleResponse.class);
        if(response.getStatus() == "OK"){
            return "OK";
        }else{
            return "FAILED";
        }
    }

    private void validation(CreateScheduleRequest createScheduleRequest){
        //TODO: validate something
    }


}
