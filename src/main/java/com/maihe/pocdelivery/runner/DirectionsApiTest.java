package com.maihe.pocdelivery.runner;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.maihe.pocdelivery.service.GoogleMapsDirectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DirectionsApiTest implements CommandLineRunner {

    @Autowired
    private GoogleMapsDirectionsService directionsService;

    @Override
    public void run(String... args) throws Exception {
        String origin = "rua olimpia de almeida prado, 27, SÃO PAULO";
        String destination = "Faculdade FIAP, São Paulo";

        List<String> waypoints = new ArrayList<>();
        String waypoint1 = "Hospiral Samaritano, São Paulo";
        String waypoint2 = "Estadio Pacaembu, São Paulo";

        waypoints.add(waypoint1);
        System.out.println("\n ------------- Calcule Rota Normal -------------");
        attemptOne(origin, destination);
        System.out.println("\n ------------- Calcule com 1 Parada -------------");
        attemptOne(origin, destination, waypoints);
        System.out.println("\n ------------- Calcule com 2 Parada -------------");
        waypoints.add(waypoint2);
        attemptOne(origin, destination, waypoints);

        System.out.println("\n ------------- Calcule com 2 Parada B -------------");
        List<String> waypoints2 = new ArrayList<>();
        waypoints2.add(waypoint2);
        attemptOne(origin, destination, waypoints2);
    }

    private void attemptOne(String origin, String destination) {
        try {
            DirectionsResult result = directionsService.getDirections(origin, destination);

            if (result.routes != null && result.routes.length > 0) {
                System.out.println("Rota encontrada:");
                System.out.println("Distância: " + result.routes[0].legs[0].distance.humanReadable);
                System.out.println("Tempo estimado com transito: " + result.routes[0].legs[0].durationInTraffic.humanReadable);
                //System.out.println("Data chegada: " + result.routes[0].legs[0].arrivalTime.toLocalDateTime().toString());
            } else {
                System.out.println("Nenhuma rota encontrada.");
            }
        } catch (IOException | InterruptedException | ApiException e) {
            System.err.println("Erro ao calcular a rota: " + e.getMessage());
        }
    }

    private void attemptOne(String origin, String destination,  List<String> waypoint) {

        try {
            DirectionsResult result = directionsService.getDirections(origin, destination, waypoint);

            if (result.routes != null && result.routes.length > 0) {
                System.out.println("Rota encontrada:");

                int legNumber=1;
                long totalDuration=0;
                long distance=0;
                for (DirectionsLeg leg : result.routes[0].legs){
                    System.out.println(legNumber + " Distância do trecho: " + leg.distance);
                    System.out.println(legNumber + " Tempo estimado trecho: " + leg.duration.humanReadable);
                    legNumber++;
                    totalDuration += leg.duration.inSeconds;
                    distance += leg.distance.inMeters;
                }
                System.out.println("Distancia total: " + convertMetros(distance) + "\n");

                System.out.println("Tempo estimado total: " + convertSeconds(totalDuration) + "\n");
                //System.out.println("Data chegada: " + result.routes[0].legs[0].arrivalTime.toLocalDateTime().toString());
            } else {
                System.out.println("Nenhuma rota encontrada.");
            }
        } catch (IOException | InterruptedException | ApiException e) {
            System.err.println("Erro ao calcular a rota: " + e.getMessage());
        }
    }

    private String convertSeconds(long totalDuration){
        long seconds,minutes,hours,days;

        seconds = totalDuration % 60;
        totalDuration /= 60;
        minutes = totalDuration % 60;
        totalDuration /= 60;
        hours = totalDuration % 24;
        totalDuration /= 24;
        days = totalDuration;

        return days+"d "+hours+"h "+minutes+"m "+seconds+"s";
    }
    private String convertMetros(long distance){
        long km,metros;

        metros = distance % 1000;
        distance /= 1000;
        km = distance;

        return km+"km "+metros+"m";
    }
}

