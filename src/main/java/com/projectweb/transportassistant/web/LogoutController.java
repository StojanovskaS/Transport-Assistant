package com.projectweb.transportassistant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping
    public String logoutUser(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
