package com.existentialponytomas.getappprospero.model;

/**
 * Created by Nessa on 01.04.2017.
 */

public class Area {
    private GPSCoordinates center;
    private double radius; //in meters


    public Area(GPSCoordinates center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean includesCoordinates(GPSCoordinates coordinates) {
        double a = center.getLatitude() - coordinates.getLatitude();
        double b = center.getLongitude() - coordinates.getLongitude();
        return a * a + b * b < radius * radius;

    }

    public GPSCoordinates getCoordinates() {
        return this.center;
    }


}
