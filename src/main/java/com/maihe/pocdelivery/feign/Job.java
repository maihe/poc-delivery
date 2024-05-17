package com.maihe.pocdelivery.feign;

import lombok.Data;

import java.util.List;

@Data
public class Job {
    private int id;
    private int service;
    private List<Integer> delivery;
    private Location location;
    private List<Integer> skills;
    private List<List<Integer>> timeWindows;

}

