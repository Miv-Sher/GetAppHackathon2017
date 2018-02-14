package com.existentialponytomas.getappprospero.presenters;

import com.existentialponytomas.getappprospero.contracts.PointsContract;
import com.existentialponytomas.getappprospero.repos.PointTransactionRepository;

/**
 * Created by Anna on 4/1/2017.
 */

public class PointsPresenter implements PointsContract.Presenter {

    PointTransactionRepository repository;
    PointsContract.View view;

    public PointsPresenter(PointsContract.View view, PointTransactionRepository repository) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void loadPointsHistory() {
        view.showPointsHistory(repository.get());
    }
}
