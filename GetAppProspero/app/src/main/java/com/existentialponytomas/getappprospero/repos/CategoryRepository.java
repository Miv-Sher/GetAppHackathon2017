package com.existentialponytomas.getappprospero.repos;

import com.existentialponytomas.getappprospero.model.Category;

import java.util.List;

/**
 * Created by Anna on 4/1/2017.
 */

public interface CategoryRepository {


    List<Category> get();

    Category getByName(String name);

    Category getById(int id);
}
