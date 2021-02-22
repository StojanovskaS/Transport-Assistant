package com.projectweb.transportassistant.model;

import com.projectweb.transportassistant.model.enumerations.MyPostsCardStatus;
import com.projectweb.transportassistant.model.enumerations.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MyPostsPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    private SiteUser user;
    @ManyToMany
    private List<Post> myposts;
    private MyPostsCardStatus status;

    public MyPostsPart() {
    }

//    public MyPostsPart(SiteUser user, List<Post> myposts, MyPostsCardStatus status) {
//        this.user = user;
//        this.myposts = myposts;
//        this.status = status;
//    }

    public MyPostsPart(SiteUser user) {
        this.user = user;
        this.status = MyPostsCardStatus.CREATED;
        this.myposts=new ArrayList<>();
    }
}
