package com.existentialponytomas.getappprospero.repos;

import com.existentialponytomas.getappprospero.contracts.PointsContract;
import com.existentialponytomas.getappprospero.model.PointTransaction;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface PointTransactionRepository {

    List<PointTransaction> get();
    List<PointTransaction> getPositive();
    List<PointTransaction> getNegative();
}
