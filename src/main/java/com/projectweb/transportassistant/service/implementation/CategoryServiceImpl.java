package com.projectweb.transportassistant.service.implementation;

import com.projectweb.transportassistant.model.Category;
import com.projectweb.transportassistant.model.exeptions.InvalidCategoryNameException;
import com.projectweb.transportassistant.model.exeptions.InvalidGradException;
import com.projectweb.transportassistant.repository.CategoryReposiotry;
import com.projectweb.transportassistant.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryReposiotry categoryReposiotry;

    public CategoryServiceImpl(CategoryReposiotry categoryReposiotry) {
        this.categoryReposiotry = categoryReposiotry;
    }

    @Override
    public List<Category> findAllByCategoryName(String categoryName) {
        return this.categoryReposiotry.findAllByCategoryNameLike(categoryName);
    }

    @Override
    public List<Category> findAllByCategoryNameAndGrad(String categoryName, String grad) {
        return this.categoryReposiotry.findAllByCategoryNameLikeAndGradLike(categoryName,grad);
    }

    @Override
    public List<Category> findAllByGrad(String grad) {
        return this.categoryReposiotry.findAllByGradLike(grad);
    }

    @Override
    public Category findByCategoryNameAndGrad(String categoryName, String grad) {
        return categoryReposiotry.findByCategoryNameLikeAndGradLike(categoryName,grad).orElseGet(() -> {
            Category cat= this.create(categoryName,grad);
            return cat;
            //ako nema da se kreira nova kategorija ako ja nema kreirano prethodno

        });


    }

    @Override
    public Category findByGrad(String grad) {
        return categoryReposiotry.findByGradLike(grad).orElseThrow(InvalidGradException::new);
    }

    @Override
    public Category findByCategoryName(String category) {
        return this.categoryReposiotry.findByCategoryNameLike(category).orElseThrow(InvalidCategoryNameException::new);
    }

    @Override
    public List<Category> listAll() {
        return this.categoryReposiotry.findAll();
    }

    @Override
    public Category create(String categoryName, String grad) {
        Category category=new Category(categoryName,grad);
        return categoryReposiotry.save(category);
    }
}
