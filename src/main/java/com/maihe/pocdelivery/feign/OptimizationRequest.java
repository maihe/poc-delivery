package com.maihe.pocdelivery.feign;

import lombok.Data;

import java.util.List;

@Data
public class OptimizationRequest {
    private List<Job> jobs;
    private List<Vehicle> vehicles;

}

