package com.existentialponytomas.getappprospero.contracts;

import com.existentialponytomas.getappprospero.model.Transaction;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface HistoryExpensesContract {
    interface Presenter {
        void loadExpensesHistory();
    }
    interface View {
        void showExpensesHistory(List<Transaction> expenses);
    }
}
