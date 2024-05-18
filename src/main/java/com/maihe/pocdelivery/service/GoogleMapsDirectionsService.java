package com.maihe.pocdelivery.service;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GoogleMapsDirectionsService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public DirectionsResult getDirections(String origin, String destination) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        DirectionsResult directionsResult = DirectionsApi.newRequest(context)
                .origin(origin)
                .destination(destination)
                .mode(TravelMode.DRIVING)
                .departureTimeNow()
                .await();
        context.shutdown();
        return directionsResult;
    }

    public DirectionsResult getDirections(String origin, String destination, List<String> waypoints) throws IOException, InterruptedException, ApiException {
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();

            DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(context)
                    .origin(origin)
                    .destination(destination)
                    .mode(TravelMode.DRIVING)
                    .departureTimeNow();

            if (waypoints != null && !waypoints.isEmpty()) {
                for (String waypoint : waypoints) {
                    directionsApiRequest.waypoints(waypoint);
                }
            }
            DirectionsResult directionsResult = directionsApiRequest.await();
            context.shutdown();
            return directionsResult;
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
            throw e;
        }

    }

   /* private double getLatitude(String location) {
        // ...
    }

    private double getLongitude(String location) {
        // ...
    }*/
}

