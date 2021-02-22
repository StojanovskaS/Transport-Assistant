package com.projectweb.transportassistant.service;

import com.projectweb.transportassistant.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllByCategoryName(String categoryName);
    List<Category> findAllByCategoryNameAndGrad(String categoryName,String grad);
    List<Category> findAllByGrad(String grad);
    Category findByCategoryNameAndGrad(String categoryName, String grad);
    Category findByGrad(String grad);
    Category findByCategoryName(String category);





    List<Category> listAll();

    Category create(String categoryName,String grad);
}
