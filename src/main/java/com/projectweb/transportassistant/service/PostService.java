package com.projectweb.transportassistant.service;

import com.projectweb.transportassistant.model.Category;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.model.SiteUser;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> listAll();
    List<Post> searchByCategory(String categoryName,String grad);
    Post save(String username, String odGrad, String opis, Float cena, GregorianCalendar den, List<Category> categoryList);
    List<Post> findByUser(String username);
    Post findByUserAndOpis(SiteUser user,String opis);
    Post findById(Long id);
    List<Post> findByOdGrad(String grad);
    List<Post> findAllByOdGradAndDoGrad(String odgrad,String dograd,String kategorijaSearch);
    Post update(Long id, String odGrad, String opis, Float cena,GregorianCalendar den, List<Category> categoryList);
    void deleteById(Long id);
}
