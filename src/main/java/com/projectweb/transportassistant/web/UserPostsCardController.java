package com.projectweb.transportassistant.web;

import com.projectweb.transportassistant.model.MyPostsPart;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.service.MyPostsService;
import com.projectweb.transportassistant.service.PostService;
import com.projectweb.transportassistant.service.SiteUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/myposts")
public class UserPostsCardController {
    private final MyPostsService myPostsService;
    private final PostService postService;
    private final SiteUserService siteUserService;

    public UserPostsCardController(MyPostsService myPostsService, PostService postService, SiteUserService siteUserService) {
        this.myPostsService = myPostsService;
        this.postService = postService;
        this.siteUserService = siteUserService;
    }
    @GetMapping
    public String getShopingPage(HttpServletRequest request, Model model){
        String username=request.getRemoteUser();
        MyPostsPart karticka=this.myPostsService.getActiveUserPostsCard(username);
        List<Post> moipostovi=this.myPostsService.listAllPostsInMyPostsCard(karticka.getId());
        model.addAttribute("moipostovi",moipostovi);
        model.addAttribute("bodyContent","mycardpage");
        return  "master-template";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteMyPostFromCard(HttpServletRequest req,@PathVariable Long id) {
        String username=req.getRemoteUser();
        SiteUser user=this.siteUserService.findByUserName(username);
        Post p=this.myPostsService.findPostById(id,username);
        this.myPostsService.deletePostFromCard(id,username);
        this.postService.deleteById(p.getId());
        return "redirect:/myposts";
    }
}
