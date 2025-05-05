package com.example.demo;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.IdempotentRepository;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    private final IdempotentRepository repo;

    public MyRouteBuilder(IdempotentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void configure() throws Exception {

        from("file:src/main/resources/input?noop=true&idempotent=true&idempotentRepository=#jpaStore")
                .to("log:processed")
                .to("file:target/output");


    }
}
