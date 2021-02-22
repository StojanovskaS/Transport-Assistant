package com.projectweb.transportassistant.web;

import com.projectweb.transportassistant.model.Category;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping({"/","/home"})
public class HomePageController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final MyPostsService myPostsService;
    private final InterestedPartService interestedPartService;
    private final SiteUserService siteUserService;

    public HomePageController(PostService postService, CategoryService categoryService, MyPostsService myPostsService, InterestedPartService interestedPartService, SiteUserService siteUserService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.myPostsService = myPostsService;
        this.interestedPartService = interestedPartService;
        this.siteUserService = siteUserService;
    }
    @GetMapping
    public String getHomePage(Model model){
        List<Post>posts=this.postService.listAll();
        model.addAttribute("lista",posts);
        List<String> categories = new ArrayList<>();
        categories.add("ИСТОЧНА");
        categories.add("ЦЕНТРАЛНА");
        categories.add("ЗАПАДНА");
        model.addAttribute("navedeni",categories);
        model.addAttribute("bodyContent","homepage");
        return "master-template";
    }
    @GetMapping("/add")
    public String showAdd(Model model) {
        List<String> categories = new ArrayList<>();
        categories.add("ИСТОЧНА");
        categories.add("ЦЕНТРАЛНА");
        categories.add("ЗАПАДНА");
        model.addAttribute("categories",categories);
        model.addAttribute("bodyContent","addpage");
        return "master-template";
    }
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Post postot = this.postService.findById(id);
        List<String> categories = new ArrayList<>();
        categories.add("ИСТОЧНА");
        categories.add("ЦЕНТРАЛНА");
        categories.add("ЗАПАДНА");
        model.addAttribute("categories",categories);
        model.addAttribute("postot",postot);
        model.addAttribute("bodyContent","addpage");
        return "master-template";
    }
    @GetMapping("/{id}/like")
    public String addLike(HttpServletRequest req,@PathVariable Long id) {
        Post postot = this.postService.findById(id);
        String username=req.getRemoteUser();
        SiteUser user=siteUserService.findByUserName(username);
        this.interestedPartService.addLike(postot.getLikepart().getId(),user);
        return "redirect:/home";
    }
    @GetMapping("/{id}/deletelike")
    public String deleteLike(HttpServletRequest req,@PathVariable Long id, Model model) {
        Post postot = this.postService.findById(id);
        String username=req.getRemoteUser();
        SiteUser user=siteUserService.findByUserName(username);
        this.interestedPartService.deleteLike(postot.getLikepart().getId(),user);
        return "redirect:/home";
    }
    @PostMapping("/home")
    public String create(HttpServletRequest req,@RequestParam String odgrad,
                         @RequestParam String opis,
                         @RequestParam Float cena,
                         @RequestParam String date,
                         @RequestParam List<String> del,
                         @RequestParam String dograd) {
        String[] datumot=date.split("/",3);
        GregorianCalendar denta= new GregorianCalendar(Integer.parseInt(datumot[2]),Integer.parseInt(datumot[1])-1,Integer.parseInt(datumot[0]));
        List<Category>categories=new ArrayList<>();
        for(int i=0;i<del.size();i++){
            categories.add(categoryService.findByCategoryNameAndGrad(del.get(i),dograd));
        }
        String username=req.getRemoteUser();
        Post post=postService.save(username,odgrad,opis,cena,denta,categories);
        this.myPostsService.addPostToMyPostsCard(post,username);
        return "redirect:/home";
    }
    @PostMapping("/home/{id}")
    public String update(HttpServletRequest req,@PathVariable Long id,
                         @RequestParam String odgrad,
                         @RequestParam String opis,
                         @RequestParam Float cena,
                         @RequestParam String date,
                         @RequestParam List<String> del,
                         @RequestParam String dograd ) {
        String[] datumot=date.split("/",3);
        GregorianCalendar denta= new GregorianCalendar(Integer.parseInt(datumot[2]),Integer.parseInt(datumot[1])-1,Integer.parseInt(datumot[0]));
        List<Category>categories=new ArrayList<>();
        for(int i=0;i<del.size();i++){
            categories.add(categoryService.findByCategoryNameAndGrad(del.get(i),dograd));
        }
        Post post=this.postService.update(id,odgrad,opis,cena,denta,categories);
        String username=req.getRemoteUser();
        this.myPostsService.updatePostInMyPostCard(post,username);
        return "redirect:/home";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(HttpServletRequest req,@PathVariable Long id) {
        Post post=this.postService.findById(id);
        String username=req.getRemoteUser();
        Long idCardtickaPostot=this.myPostsService.findPostIdinCard(post);
        this.myPostsService.deletePostFromCard(idCardtickaPostot,post.getUser().getUsername());
        this.postService.deleteById(id);
        return "redirect:/home";
    }

}
