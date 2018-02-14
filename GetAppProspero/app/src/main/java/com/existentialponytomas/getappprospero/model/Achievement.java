package com.existentialponytomas.getappprospero.model;


/**
 * Created by Anna on 4/01/2017.
 */

public class Achievement {
    private User user;
    private AchievementType type;
    private long date;

    public Achievement(){}

    public Achievement(AchievementType type, long date) {
        this.type = type;
        this.date = date;
    }

    public AchievementType getType() {
        return type;
    }

    public long getDate() {
        return date;
    }
}

