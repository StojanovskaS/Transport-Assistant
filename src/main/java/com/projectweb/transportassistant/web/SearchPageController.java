package com.projectweb.transportassistant.web;

import com.projectweb.transportassistant.model.Category;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchPageController {
    private final PostService postService;

    public SearchPageController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/istocna")
    public String getIstocna(@RequestParam(required = false) String odGradSearch,
                             @RequestParam (required = false) String doGradSearch,
                             Model model){
        List<Post> posts=new ArrayList<>();
       if (doGradSearch!=null && odGradSearch!=null){
            posts=this.postService.findAllByOdGradAndDoGrad(odGradSearch,doGradSearch,"ИСТОЧНА");

        }else if(doGradSearch!=null){
            posts=this.postService.searchByCategory("ИСТОЧНА",doGradSearch);
        }else{
            posts=this.postService.searchByCategory("ИСТОЧНА","");
        }

        model.addAttribute("lista",posts);
        model.addAttribute("bodyContent","searchpage");
        return "master-template";
    }
    @GetMapping("/centralna")
    public String getCentralna(@RequestParam(required = false) String odGradSearch,
                               @RequestParam (required = false) String doGradSearch,
                               Model model){
        List<Post> posts=new ArrayList<>();
        if (doGradSearch!=null && odGradSearch!=null){
            posts=this.postService.findAllByOdGradAndDoGrad(odGradSearch,doGradSearch,"ЦЕНТРАЛНА");

        }else if(doGradSearch!=null){
            posts=this.postService.searchByCategory("ЦЕНТРАЛНА",doGradSearch);
        }else{
            posts=this.postService.searchByCategory("ЦЕНТРАЛНА","");
        }

        model.addAttribute("lista",posts);
        model.addAttribute("bodyContent","searchpage");
        return "master-template";
    }
    @GetMapping("/zapadna")
    public String getZapadna(@RequestParam(required = false) String odGradSearch,
                             @RequestParam (required = false) String doGradSearch,Model model){
        List<Post> posts=new ArrayList<>();
        if (doGradSearch!=null && odGradSearch!=null){
            posts=this.postService.findAllByOdGradAndDoGrad(odGradSearch,doGradSearch,"ЗАПАДНА");

        }else if(doGradSearch!=null){
            posts=this.postService.searchByCategory("ЗАПАДНА",doGradSearch);
        }else{
            posts=this.postService.searchByCategory("ЗАПАДНА","");
        }
        model.addAttribute("lista",posts);
        model.addAttribute("bodyContent","searchpage");
        return "master-template";
    }
}
