package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = {
//        "org.apache.camel.processor.idempotent.jpa"
//})
public class CamelApp {
    public static void main(String[] args) {
        SpringApplication.run(CamelApp.class, args);
    }
}
