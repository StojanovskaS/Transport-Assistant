package com.projectweb.transportassistant.model;

import com.projectweb.transportassistant.model.enumerations.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class SiteUser {
    @Id
    String username;
    String firstname;
    String lastname;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    List<Post> posts;

    public SiteUser(String username, String email, String firstname, String lastname, String password, Role role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.email=email;
    }

    public SiteUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public SiteUser() {
    }
}
