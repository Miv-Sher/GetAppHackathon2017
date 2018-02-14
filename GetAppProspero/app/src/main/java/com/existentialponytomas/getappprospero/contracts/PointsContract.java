package com.existentialponytomas.getappprospero.contracts;

import com.existentialponytomas.getappprospero.model.PointTransaction;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface PointsContract {
    interface View {
        void showPointsHistory(List<PointTransaction> pointsHistory);
    }
    interface Presenter {
        void loadPointsHistory();
    }
}
