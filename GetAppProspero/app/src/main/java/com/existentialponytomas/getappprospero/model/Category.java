package com.existentialponytomas.getappprospero.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 4/01/2017.
 */



public class Category {


    public enum Type {EXPENSE, INCOME, SAVINGS }

    private int id;
    private String name;
    private Type type;
    public Category() {

    }

    public Category(int id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        Category other = (Category) obj;
        return other.id == this.id;
    }
}
