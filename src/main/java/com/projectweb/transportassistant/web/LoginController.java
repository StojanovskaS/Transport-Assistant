package com.projectweb.transportassistant.web;

import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.exeptions.InvalidUsercCredentialException;
import com.projectweb.transportassistant.service.SiteUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final SiteUserService siteUserService;
    public LoginController(SiteUserService siteUserService) {
        this.siteUserService = siteUserService;
    }
    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String error,Model model){
        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","loginpage");
        return  "master-template";
    }
    @PostMapping
    public String najava(HttpServletRequest request, Model model){
        SiteUser user = null;
        try{
            user = (SiteUser) this.siteUserService.login(request.getParameter("username"),request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUsercCredentialException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent","loginpage");
            return  "master-template";

        }

    }



}
