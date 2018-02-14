package com.existentialponytomas.getappprospero.contracts;

import com.existentialponytomas.getappprospero.model.Budget;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface BudgetContract {
    interface View {
        void showBudgets(List<Budget> budgets, List<Double> transactionContracts);
    }
    interface Presenter {
        void loadBudgets();
    }
}
