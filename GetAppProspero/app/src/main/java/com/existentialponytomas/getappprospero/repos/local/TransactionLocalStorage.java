package com.existentialponytomas.getappprospero.repos.local;

import com.existentialponytomas.getappprospero.model.Area;
import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.model.GPSCoordinates;
import com.existentialponytomas.getappprospero.model.Transaction;
import com.existentialponytomas.getappprospero.repos.CategoryRepository;
import com.existentialponytomas.getappprospero.repos.TransactionRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public class TransactionLocalStorage implements TransactionRepository {

    private static TransactionLocalStorage instance;

    List<Transaction> transactions;
    CategoryRepository categoryRepository;

    private TransactionLocalStorage() {
        categoryRepository = CategoryLocalStorage.getInstance();
        transactions = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(2017, 04, 02, 04, 05, 06);
        transactions.add(new Transaction(-230, categoryRepository.getByName("Еда"), c.getTimeInMillis(), new GPSCoordinates(2000, 3000), "Coffee House", "Чизкейк"));
        transactions.add(new Transaction(65000, categoryRepository.getByName("Зарплата"), c.getTimeInMillis(),  new GPSCoordinates(2000, 3000), "Raiffeisen", "Зарплата"));
        transactions.add(new Transaction(-1200, categoryRepository.getByName("Одежда"), c.getTimeInMillis(), new GPSCoordinates(2000, 3000), "H&M", "Джинсы"));
        transactions.add(new Transaction(2500, categoryRepository.getByName("Зарплата"), c.getTimeInMillis(),  new GPSCoordinates(2000, 3000), "Вася Пупкин", "Ученик"));
        transactions.add(new Transaction(-800, categoryRepository.getByName("Развлечения"), c.getTimeInMillis(), new GPSCoordinates(2000, 3000), "Steam", "Divinity: Original Sin"));
        transactions.add(new Transaction(-2500, categoryRepository.getByName("Развлечения"), c.getTimeInMillis(),  new GPSCoordinates(2000, 3000), "Steam", "Witcher 3"));
        transactions.add(new Transaction(-5600, categoryRepository.getByName("Здоровье"), c.getTimeInMillis(), new GPSCoordinates(2000, 3000), "MedClinic", "Стоматолог"));
        transactions.add(new Transaction(200, categoryRepository.getByName("Зарплата"), c.getTimeInMillis(),  new GPSCoordinates(2000, 3000), "Клиент", "Чаевые"));


    }

    public  static TransactionLocalStorage getInstance() {
        if (instance == null) {
            instance = new TransactionLocalStorage();
        }
        return  instance;
    }

    @Override
    public List<Transaction> getExpenses() {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).isExpense()) {
                result.add(transactions.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getIncomes() {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).isIncome()) {
                result.add(transactions.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getExpensesByPeriod(long from, long to) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).isExpense() && transactions.get(i).isInPeriod(from, to)) {
                result.add(transactions.get(i));
            }
        }
        return result;
    }

    @Override
    public double getExpensesSumForBudget(Budget budget) {
        double result = 0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).belongsToBudget(budget)) {
                result -= transactions.get(i).getAmount();
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getIncomesByPeriod(long from, long to) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).isIncome() && transactions.get(i).isInPeriod(from, to)) {
                result.add(transactions.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionsFromArea(Area area) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).isInArea(area)) {
                result.add(transactions.get(i));
            }
        }
        return result;
    }

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
}
