package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Value("${myapp.camel.file.input.directory:src/main/resources/input}")
    private String inputDirectory;

    @Value("${myapp.camel.file.processing.directory:src/main/resources/processing}")
    private String processingDirectory;

    @Override
    public void configure() throws Exception {
        from("file:" + inputDirectory +
                "?preMove=" + processingDirectory + // Sposta il file prima del processamento
                "&delete=false" +                   // non cancellare da processingDirectory dopo la fine esecuzione rotta
                "&initialDelay=1000&delay=5000")    // polling
                .log("File originale ${header.CamelFileNameOriginal} pre-mosso in: " + processingDirectory)
                .log("Inizio processamento per il file: ${header.CamelFilePath}") // CamelFilePath punta al file in processingDirectory

                // Qui inserisci la logica per avviare il job Kubernetes.
                // L'endpoint e i parametri dipenderanno dalla tua configurazione Kubernetes e dal componente Camel Kubernetes.
                // Esempio concettuale (dovrai adattarlo):
                // .toF("kubernetes-jobs:///?namespace=default&jobName=my-job-%s&<altri-parametri>", simple("${header.CamelFileNameOriginal}"))
                .log("Avvio del job Kubernetes per il file ${header.CamelFileNameOriginal} (che si trova in " + processingDirectory + ") richiesto.")

        // La rotta Camel termina qui. Il file rimane nella directory 'processingDirectory'.
        // Il job Kubernetes è ora responsabile del file.

        ;
    }
}