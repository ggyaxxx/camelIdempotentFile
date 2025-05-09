package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Value("${myapp.camel.file.input.directory:input}")
    private String inputDirectory;

    @Value("${myapp.camel.file.processing.directory:processing}")
    private String processingDirectory;

    @Override
    public void configure() throws Exception {
        from("file:input"+
                "?preMove=processing" + // Sposta il file prima del processamento
                "&delete=false" +                   // non cancellare da processingDirectory dopo la fine esecuzione rotta
                "&initialDelay=1000&delay=5000")    // polling
                .log("File originale ${header.CamelFileNameOriginal} pre-mosso in: " + processingDirectory)
                .log("Inizio processamento per il file: ${header.CamelFilePath}") // CamelFilePath punta al file in processingDirectory


                .log("Avvio del job Kubernetes per il file ${header.CamelFileNameOriginal}")


        ;
    }
}