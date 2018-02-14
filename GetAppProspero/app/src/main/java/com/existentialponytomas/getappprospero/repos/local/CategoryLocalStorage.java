package com.existentialponytomas.getappprospero.repos.local;

import com.existentialponytomas.getappprospero.model.Category;
import com.existentialponytomas.getappprospero.repos.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public class CategoryLocalStorage implements CategoryRepository {
    List<Category> categories;
    private static CategoryLocalStorage instance;

    private CategoryLocalStorage() {
        categories = new ArrayList<>();
        categories.add(new Category(-1, "Не назначено", Category.Type.EXPENSE) );
        categories.add(new Category(0, "Еда", Category.Type.EXPENSE) );
        categories.add(new Category(1, "Одежда", Category.Type.EXPENSE));
        categories.add(new Category(2, "Развлечения", Category.Type.EXPENSE));
        categories.add(new Category(3, "Транспорт", Category.Type.EXPENSE));
        categories.add(new Category(4, "Здоровье", Category.Type.EXPENSE));
        categories.add(new Category(5, "Прочеее", Category.Type.EXPENSE));
        categories.add(new Category(6, "Зарплата", Category.Type.INCOME));



    }

    public static CategoryLocalStorage getInstance() {
        if (instance == null) {
            instance = new CategoryLocalStorage();
        }
        return  instance;
    }

    public List<Category> get() {
       return categories;
    }

    @Override
    public Category getByName(String name) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getName().equals(name)) {
                return categories.get(i);
            }
        }
        return null;
    }

    @Override
    public Category getById(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return categories.get(i);
            }
        }
        return null;
    }
}
