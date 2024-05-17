package com.maihe.pocdelivery.feign;

import lombok.Data;

import java.util.List;

@Data
public class OptimizationResponse {
    private String status;
    private List<Route> routes;
    private Unassigned unassigned;


    // Inner class Route
    public static class Route {
        private List<Step> steps;
    }

    // Inner class Step
    public static class Step {
        private int type;
        private Job job;
        private int arrival;
        private int service;
        private Location location;
    }

    // Inner class Unassigned
    public static class Unassigned {
        private List<Job> jobs;
    }
}

