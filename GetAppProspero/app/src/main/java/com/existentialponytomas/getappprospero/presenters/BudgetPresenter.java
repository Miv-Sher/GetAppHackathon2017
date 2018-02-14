package com.existentialponytomas.getappprospero.presenters;

import com.existentialponytomas.getappprospero.contracts.BudgetContract;
import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.repos.BudgetRepository;
import com.existentialponytomas.getappprospero.repos.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public class BudgetPresenter implements BudgetContract.Presenter {

    BudgetContract.View view;
    BudgetRepository budgetRepository;
    TransactionRepository transactionRepository;
    public BudgetPresenter(BudgetContract.View view, BudgetRepository repository,
                           TransactionRepository transactionRepository) {
        this.view = view;
        this.budgetRepository = repository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void loadBudgets() {

        List<Budget> budgets = budgetRepository.get();
        List<Double> transactionSums = new ArrayList<>();
        for (int i = 0; i < budgets.size(); i++) {
            transactionSums.add(transactionRepository.getExpensesSumForBudget(budgets.get(i)));
        }
        view.showBudgets(budgets, transactionSums);
    }
}
