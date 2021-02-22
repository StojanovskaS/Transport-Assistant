package com.projectweb.transportassistant.web;

import com.projectweb.transportassistant.model.enumerations.Role;
import com.projectweb.transportassistant.model.exeptions.InvalidUsercCredentialException;
import com.projectweb.transportassistant.model.exeptions.PasswordsdoNotMatchException;
import com.projectweb.transportassistant.service.SiteUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final SiteUserService siteUserService;

    public RegisterController(SiteUserService siteUserService) {
        this.siteUserService = siteUserService;
    }
    @GetMapping
    public String getRegisterPage(@RequestParam(required =false)String error, Model model){
        if (error!= null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);

        }
        model.addAttribute("bodyContent","registerpage");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String email,
                           @RequestParam Role role){
        try{
           this.siteUserService.register(username,email,firstname,lastname,password,repeatedPassword,role);
            return "redirect:/login";

        }catch (InvalidUsercCredentialException | PasswordsdoNotMatchException exception){
            return "redirect:/register?error="+exception.getMessage();
        }

    }
}
