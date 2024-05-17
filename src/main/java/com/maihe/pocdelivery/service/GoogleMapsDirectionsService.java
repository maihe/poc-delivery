package com.maihe.pocdelivery.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsDirectionsService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public DirectionsResult getDirections(String origin, String destination) throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        //LatLng originLatLng = new LatLng(getLatitude(origin), getLongitude(origin));
        //LatLng destinationLatLng = new LatLng(getLatitude(destination), getLongitude(destination));

        return DirectionsApi.newRequest(context)
                .origin(origin)
                .destination(destination)
                .mode(TravelMode.DRIVING)
                .await();
    }

   /* private double getLatitude(String location) {
        // ...
    }

    private double getLongitude(String location) {
        // ...
    }*/
}

