package com.projectweb.transportassistant.service.implementation;

import com.projectweb.transportassistant.model.Category;
import com.projectweb.transportassistant.model.InterestedPart;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.exeptions.InvalidCategoryNameException;
import com.projectweb.transportassistant.model.exeptions.PostNotFound;
import com.projectweb.transportassistant.model.exeptions.UserNotFoundExeption;
import com.projectweb.transportassistant.repository.InterestedPartRepository;
import com.projectweb.transportassistant.repository.PostRepository;
import com.projectweb.transportassistant.repository.SiteUserRepository;
import com.projectweb.transportassistant.service.CategoryService;
import com.projectweb.transportassistant.service.PostService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final SiteUserRepository siteUserRepository;
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final InterestedPartRepository interestedPartRepository;
    public PostServiceImpl(SiteUserRepository siteUserRepository, PostRepository postRepository, CategoryService categoryService, InterestedPartRepository interestedPartRepository) {
        this.siteUserRepository = siteUserRepository;
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.interestedPartRepository = interestedPartRepository;
    }


    @Override
    public List<Post> listAll() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> searchByCategory(String categoryName, String grad) {
        List<Category> c=new ArrayList<>();
        String catn1="%"+categoryName+"%";
        String grad1="%"+grad+"%";
        if (categoryName!=null && grad!=null && !categoryName.isEmpty() && !grad.isEmpty()){
            c=categoryService.findAllByCategoryNameAndGrad(catn1,grad1);
        }else if(categoryName!=null && !categoryName.isEmpty()){
            c=categoryService.findAllByCategoryName(catn1);
        }else if(grad !=null && !grad.isEmpty()){
            c=categoryService.findAllByGrad(grad1);
        }else{
            throw new InvalidCategoryNameException();
        }
        List<Post>posts=new ArrayList<>();
        for(int i=0;i<c.size();i++){
            List<Post>del=postRepository.findAllByCategoryListContaining(c.get(i));
            for(int j=0;j<del.size();j++){
                posts.add(del.get(j));
            }
        }
        return posts;
    }

    @Override
    public Post save(String username, String odGrad, String opis, Float cena, GregorianCalendar den, List<Category> categoryList) {
        SiteUser user=siteUserRepository.findByUsername(username).orElseThrow(UserNotFoundExeption::new);
//        SiteUser user=siteUserRepository.findByUsername(username).orElseGet(()->{
//            return siteUserRepository.save(new SiteUser(username));
//        });
//        ako ne mi uspee ko prvoto
        InterestedPart interestedPart=this.interestedPartRepository.save(new InterestedPart(0));
        return postRepository.save(new Post(user,odGrad,opis,cena,den,categoryList,interestedPart));
    }

    @Override
    public List<Post> findByUser(String username) {
        SiteUser user=siteUserRepository.findByUsername(username).orElseThrow(UserNotFoundExeption::new);

        return postRepository.findAllByUser(user);
    }

    @Override
    public Post findByUserAndOpis(SiteUser user, String opis) {
        return this.postRepository.findByUserAndOpisLike(user,opis);
    }

    @Override
    public Post findById(Long id) {
        return this.postRepository.findById(id).orElseThrow(PostNotFound::new);
    }

    @Override
    public List<Post> findByOdGrad(String grad) {
        String g1="%"+grad+"%";

        return this.postRepository.findAllByOdGradLike(g1);
//poso u baza mi se zacuvani za razlicni kategorii poradi opis
    }

    @Override
    public List<Post> findAllByOdGradAndDoGrad(String odgrad, String dograd,String kategorijaSearch) {
        String odgrad1="%"+odgrad+"%";
        String dograd1="%"+dograd+"%";
        String kateg1="%"+kategorijaSearch+"%";
        List<Category>categories=this.categoryService.findAllByCategoryNameAndGrad(kateg1,dograd1);
        List<Post>posts=new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            List<Post>del=postRepository.findAllByOdGradLikeAndCategoryListContaining(odgrad1,categories.get(i));
            for(int j=0;j<del.size();j++){
                posts.add(del.get(j));
            }
        }
        return posts;
    }

    @Override
    public Post update(Long id, String odGrad, String opis, Float cena, GregorianCalendar den, List<Category> categoryList) {
        Post post = this.findById(id);
        post.setCategoryList(categoryList);
        post.setOdGrad(odGrad);
        post.setOpis(opis);
        post.setCena(cena);
        post.setDen(den);
        return this.postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);

    }
}
