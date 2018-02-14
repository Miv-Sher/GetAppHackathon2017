package com.existentialponytomas.getappprospero.model;

/**
 * Created by Anna on 4/01/2017.
 */

public class Transaction {

    private Category category;
    private long date;
    private GPSCoordinates coordinates;
    private String source;
    private double amount;
    private String comment;


    public Transaction() {}

    public Transaction(double amount, Category category,
                       long date, GPSCoordinates coordinates, String source, String comment) {

        this.amount = amount;
        this.category = category;
        this.date = date;
        this.coordinates = coordinates;
        this.source = source;
        this.comment = comment;
    }

    public boolean isInPeriod(long start, long finish) {
        return date >= start && date <= finish;
    }

    public boolean isExpense() {
        return amount < 0;
    }
    public boolean isIncome() {
        return amount > 0;
    }


    public Category getCategory() {
        return category;
    }

    public double getAmount() {return amount;}

    public long getDate() {
        return date;
    }

    public boolean isInArea (Area area) {
        return area.includesCoordinates(coordinates);
    }

    public GPSCoordinates getCoordinates() {
        return coordinates;
    }

    public String getSource() {
        return source;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComment (String comment) {this.comment = comment; }

    public String getComment() {return comment;}

    public boolean belongsToBudget(Budget budget) {
        return date >= budget.getTimeStart() &&
                date <= budget.getTimeFinish() &&
                category.equals(budget.getCategory());
    }

}


