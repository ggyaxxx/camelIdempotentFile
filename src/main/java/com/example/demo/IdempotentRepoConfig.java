package com.example.demo;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.processor.idempotent.jdbc.JdbcOrphanLockAwareIdempotentRepository;
import org.apache.camel.spi.IdempotentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdempotentRepoConfig {

    @Bean
    public IdempotentRepository idempotentRepository(DataSource dataSource, CamelContext camelContext) {
        JdbcOrphanLockAwareIdempotentRepository repo =
                new JdbcOrphanLockAwareIdempotentRepository(dataSource, "MyRouteBuilder", camelContext);

        repo.setLockMaxAgeMillis(5 * 60 * 1000); //in caso di più istanze che vanno ad insistere sullo stesso db, meglio gestire esplicitamente il lock della tabella

        repo.setLockKeepAliveIntervalMillis(60 * 1000);


        return repo;
    }
}
