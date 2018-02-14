package com.existentialponytomas.getappprospero.repos;

import com.existentialponytomas.getappprospero.model.Area;
import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.model.Transaction;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface TransactionRepository {
    List<Transaction> getExpenses();
    List<Transaction> getIncomes();
    List<Transaction> getExpensesByPeriod(long from, long to);
    List<Transaction> getIncomesByPeriod(long from, long to);
    List<Transaction> getTransactionsFromArea(Area area);
    double getExpensesSumForBudget(Budget budget);
}
