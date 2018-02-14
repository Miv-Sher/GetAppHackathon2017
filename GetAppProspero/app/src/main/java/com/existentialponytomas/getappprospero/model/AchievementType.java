package com.existentialponytomas.getappprospero.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Anna on 4/01/2017.
 */

public class AchievementType {

    private static Collection<AchievementType> types;

    {
        types = new ArrayList<>();
        //add some types
    }

    private String description;
    private String name;


    public AchievementType() {

    }

    public AchievementType(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public static Collection<AchievementType> getTypes() {
        return types;
    }
}
