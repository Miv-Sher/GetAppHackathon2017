package com.existentialponytomas.getappprospero.model;

/**
 * Created by Anna on 4/01/2017.
 */

public class User {
    private static int idCounter;

    private  int id = 0;
    private String name;
    private int avatar;
    private double points;


    public User(String name, int avatar, double points) {
        super();
        this.name = name;
        this.avatar = avatar;
        this.points = points;
    }

    public String getName() {
        return name;
    }
    public double getPoints() {return points;}
    public int getAvatar() {return avatar;}

    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        return id == other.id;
    }
}
