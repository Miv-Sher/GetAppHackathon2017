package com.existentialponytomas.getappprospero.presenters;

import com.existentialponytomas.getappprospero.contracts.HistoryIncomeContract;
import com.existentialponytomas.getappprospero.repos.TransactionRepository;

/**
 * Created by Anna on 4/1/2017.
 */

public class HistoryIncomePresenter implements HistoryIncomeContract.Presenter {

    private TransactionRepository transactionRepository;
    private HistoryIncomeContract.View view;

    public HistoryIncomePresenter(HistoryIncomeContract.View view,
                                  TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.view = view;
    }

    @Override
    public void loadIncomeHistory() {
        view.showIncomeHistory(transactionRepository.getIncomes());
    }
}
