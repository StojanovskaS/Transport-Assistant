package com.projectweb.transportassistant.repository;

import com.projectweb.transportassistant.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryReposiotry extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryNameLike(String categoryName);
    Optional<Category> findByCategoryNameLikeAndGradLike(String categoryname,String grad);
    Optional<Category> findByGradLike(String grad);
    List<Category> findAllByCategoryNameLike(String categoryName);
    List<Category> findAllByCategoryNameLikeAndGradLike(String categoryname,String grad);
    List<Category> findAllByGradLike(String grad);
}
