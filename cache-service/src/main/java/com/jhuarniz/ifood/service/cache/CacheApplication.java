package com.jhuarniz.ifood.service.cache;

import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableIgniteRepositories
public class CacheApplication {



    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }



}