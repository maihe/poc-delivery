package com.maihe.pocdelivery.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodedWaypoint;
import com.maihe.pocdelivery.service.GoogleMapsDirectionsService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class DirectionsController {
    private GoogleMapsDirectionsService directionsService;

    public DirectionsController(GoogleMapsDirectionsService directionsService) {
        this.directionsService = directionsService;
    }

    public void test (){
        try {
            DirectionsResult directions = directionsService.getDirections("cep 01151010", "cep 01110200");
            GeocodedWaypoint geocodedWaypoint = directions.geocodedWaypoints[0];
            DirectionsRoute route = directions.routes[0];


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
