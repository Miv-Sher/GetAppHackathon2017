package com.existentialponytomas.getappprospero.repos;

import com.existentialponytomas.getappprospero.model.Budget;
import com.existentialponytomas.getappprospero.model.Category;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface BudgetRepository {
    List<Budget> get();
    Budget getForCategory(Category category);
}
