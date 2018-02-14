package com.existentialponytomas.getappprospero.repos.local;

import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.model.Category;
import com.existentialponytomas.getappprospero.repos.BudgetRepository;
import com.existentialponytomas.getappprospero.repos.CategoryRepository;
import com.existentialponytomas.getappprospero.repos.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public class BudgetLocalStorage implements BudgetRepository {


    private static BudgetLocalStorage instance;

    public static BudgetLocalStorage getInstance() {
        if (instance == null) {
            instance = new BudgetLocalStorage();
        }
        return instance;
    }

    List<Budget> budgets;
    CategoryRepository categoryRepository;
    private BudgetLocalStorage() {
        categoryRepository = CategoryLocalStorage.getInstance();
        budgets = new ArrayList<>();
        budgets.add(new Budget(categoryRepository.getByName("Еда"), 10000));
        budgets.add(new Budget(categoryRepository.getByName("Одежда"), 15000));
        budgets.add(new Budget(categoryRepository.getByName("Развлечения"),7000));
        budgets.add(new Budget(categoryRepository.getByName("Транспорт"), 4000));
        budgets.add(new Budget(categoryRepository.getByName("Здоровье"), 10000));
        budgets.add(new Budget(categoryRepository.getByName("Прочеее"), 19000));
        budgets.add(new Budget(categoryRepository.getByName("Зарплата"), 65000));

    }

    @Override
    public List<Budget> get() {
        return budgets;
    }
    @Override
    public Budget getForCategory(Category category) {
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getCategory().equals(category)) {
                return budgets.get(i);
            }
        }
        return  new Budget(category, 0);
    }
}
