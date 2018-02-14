package com.existentialponytomas.getappprospero.model;



/**
 * Created by Nessa on 01.04.2017.
 */

public class PointTransaction {
    private double amount;
    private long date;
    private String reason;
    private int userId;

    public PointTransaction(double amount, long date, String reason) {
        this.amount = amount;
        this.date = date;
        this.reason = reason;
    }

    public  boolean isPositive() {
        return amount >=0;
    }

    public  boolean isNegative() {
        return amount >=0;
    }


    public double getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }
}
