package com.existentialponytomas.getappprospero.model;

import java.util.Calendar;

/**
 * Created by Nessa on 01.04.2017.
 */

public class Budget {
    private User user;
    private Category category;
    private double sum;
    private double spent;
    private long timeStart;
    private long timeFinish;

    public long getTimeStart() {
        return timeStart;
    }

    public long getTimeFinish() {
        return timeFinish;
    }

    public Budget(Category category, double sum) {
        this.category = category;
        this.sum = sum;
        Calendar start = Calendar.getInstance();
        Calendar finish = Calendar.getInstance();
        start.set(2017, 04, 01, 00, 00, 00);
        finish.set(2017, 05, 01, 00, 00, 00);
        this.timeStart = start.getTimeInMillis();
        this.timeFinish = finish.getTimeInMillis();
    }


    public double getSum() {
        return sum;
    }

    public  void  setSum(double sum)
    {
        this.sum = sum;
    }

    public Category getCategory() {
        return category;
    }
}
