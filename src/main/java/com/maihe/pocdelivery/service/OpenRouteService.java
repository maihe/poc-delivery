package com.maihe.pocdelivery.service;

import com.maihe.pocdelivery.feign.OpenRouteServiceFeignClient;
import com.maihe.pocdelivery.feign.OptimizationRequest;
import com.maihe.pocdelivery.feign.OptimizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenRouteService {

    @Value("${openrouteservice.api.key}")
    private String apiKey;

    private final OpenRouteServiceFeignClient feignClient;


    public OptimizationResponse optimizeRoute(OptimizationRequest request) {
        String authorizationHeader = "Bearer " + apiKey;
        return feignClient.optimizeRoute(authorizationHeader, request);
    }
}
