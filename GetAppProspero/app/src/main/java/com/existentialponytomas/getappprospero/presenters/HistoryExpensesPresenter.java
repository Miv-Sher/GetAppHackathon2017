package com.existentialponytomas.getappprospero.presenters;

import com.existentialponytomas.getappprospero.contracts.HistoryExpensesContract;
import com.existentialponytomas.getappprospero.repos.TransactionRepository;

/**
 * Created by Anna on 4/1/2017.
 */

public class HistoryExpensesPresenter implements  HistoryExpensesContract.Presenter{
    private TransactionRepository transactionRepository;
    private HistoryExpensesContract.View view;

    public HistoryExpensesPresenter(HistoryExpensesContract.View view,
                                  TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.view = view;
    }

    @Override
    public void loadExpensesHistory() {
        view.showExpensesHistory(transactionRepository.getExpenses());
    }
}
