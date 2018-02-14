package com.existentialponytomas.getappprospero.model;

/**
 * Created by Nessa on 01.04.2017.
 */


public class GPSCoordinates {
    private double longitude;
    private double latitude;

    public GPSCoordinates(){}

    public GPSCoordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
