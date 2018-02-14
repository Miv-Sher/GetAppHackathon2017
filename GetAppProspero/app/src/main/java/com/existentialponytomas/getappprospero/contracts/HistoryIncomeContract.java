package com.existentialponytomas.getappprospero.contracts;

import com.existentialponytomas.getappprospero.model.Transaction;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface HistoryIncomeContract {
    interface Presenter {
        void loadIncomeHistory();
    }
    interface View {
        void showIncomeHistory(List<Transaction> incomes);
    }
}
