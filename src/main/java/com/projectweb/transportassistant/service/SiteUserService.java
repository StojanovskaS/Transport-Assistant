package com.projectweb.transportassistant.service;

import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.enumerations.Role;

import java.util.Optional;

public interface SiteUserService {
    SiteUser login(String username, String password);
    SiteUser register(String username, String email,String firstname, String lastname, String password, String repeatpassword, Role role);
    SiteUser findByUserName(String username);

}
