package com.maihe.pocdelivery.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openrouteservice", url = "${openrouteservice.api.url}")
@Component
public interface OpenRouteServiceFeignClient {

    @PostMapping(value = "/optimization", consumes = "application/json", produces = {"application/json", "application/geo+json", "application/gpx+xml", "img/png"})
    OptimizationResponse optimizeRoute(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody OptimizationRequest request
    );
}
