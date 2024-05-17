package com.maihe.pocdelivery.runner;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.maihe.pocdelivery.service.GoogleMapsDirectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DirectionsApiTest implements CommandLineRunner {

    @Autowired
    private GoogleMapsDirectionsService directionsService;

    @Override
    public void run(String... args) throws Exception {
        String origin = "rua olimpia de almeida prado, 27, SÃO PAULO";
        String destination = "Rua Lopes de oliveira 487, São Paulo";

        try {
            DirectionsResult result = directionsService.getDirections(origin, destination);

            if (result.routes != null && result.routes.length > 0) {
                System.out.println("Rota encontrada:");
                System.out.println("Distância: " + result.routes[0].legs[0].distance.humanReadable);
                System.out.println("Tempo estimado: " + result.routes[0].legs[0].duration.humanReadable);
                System.out.println("Data chegada: " + result.routes[0].legs[0].arrivalTime.toLocalDateTime().toString());
                // ... (Outras informações da rota)
            } else {
                System.out.println("Nenhuma rota encontrada.");
            }
        } catch (IOException | InterruptedException | ApiException e) {
            System.err.println("Erro ao calcular a rota: " + e.getMessage());
        }
    }
}

