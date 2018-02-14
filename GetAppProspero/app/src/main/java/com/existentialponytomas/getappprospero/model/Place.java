package com.existentialponytomas.getappprospero.model;

/**
 * Created by Nessa on 01.04.2017.
 */

public class Place {
    private int userId;
    private Area location;
    private String name;
    private String comment;
    private boolean isCool;

    public boolean isCool() {
        return isCool;
    }

    public boolean isDanger() {
        return !isCool;
    }

    public Place() {}

    public Place(Area location, String name, String comment, boolean isCool) {
        this.location = location;
        this.name = name;
        this.comment = comment;
        this.isCool = isCool;
    }

    public Area getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
