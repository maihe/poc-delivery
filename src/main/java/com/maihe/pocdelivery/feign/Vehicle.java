package com.maihe.pocdelivery.feign;

import lombok.Data;

import java.util.List;

@Data
public class Vehicle {
    private int id;
    private String profile;
    private Location start;
    private Location end;
    private List<Integer> capacity;
    private List<Integer> skills;
    private List<Integer> timeWindow;

}
