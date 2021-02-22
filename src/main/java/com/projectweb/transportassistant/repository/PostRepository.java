package com.projectweb.transportassistant.repository;

import com.projectweb.transportassistant.model.Category;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByCategoryListContaining(Category category);
    List<Post> findAllByUser(SiteUser user);
    Post findByUserAndOpisLike(SiteUser user,String opis);
    List<Post> findAllByOdGradLike(String grad);
    List<Post>findAllByOdGradLikeAndCategoryListContaining(String odgrad,Category category);

}
