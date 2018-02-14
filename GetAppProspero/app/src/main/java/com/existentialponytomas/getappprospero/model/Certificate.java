package com.existentialponytomas.getappprospero.model;

import android.net.Uri;

/**
 * Created by Nessa on 02.04.2017.
 */

public class Certificate {

    private int icon;
    private String sum_amount;
    private String points_amount;
    private Uri url;

    public Certificate(int icon, String sum_amount,String points_amount, String url) {
        this.icon = icon;
        this.sum_amount = sum_amount;
        this.points_amount = points_amount;
        this.url = Uri.parse(url);
    }

    public int getIcon() {
        return icon;
    }

    public String getPoints_amount() {
        return points_amount;
    }

    public String getSum_amount() {
        return sum_amount;
    }

    public Uri getUrl() {
        return url;
    }
}
