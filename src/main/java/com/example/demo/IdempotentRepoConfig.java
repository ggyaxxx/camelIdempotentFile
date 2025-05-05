package com.example.demo;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.processor.idempotent.jdbc.JdbcOrphanLockAwareIdempotentRepository;
import org.apache.camel.spi.IdempotentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;
import org.apache.camel.processor.idempotent.jpa.JpaMessageIdRepository;
import org.apache.camel.spi.IdempotentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdempotentRepoConfig {



    @Bean(name="jpaStore")
    public IdempotentRepository jpaIdempotentRepository(EntityManagerFactory entityManagerFactory) {
        return new JpaMessageIdRepository(entityManagerFactory, "MyRouteBuilder");
    }
}
