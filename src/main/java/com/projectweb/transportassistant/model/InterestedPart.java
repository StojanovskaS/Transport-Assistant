package com.projectweb.transportassistant.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class InterestedPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer likesNo;
    @ManyToMany
    List<SiteUser> usersLikes;

    public InterestedPart() {

    }

    public InterestedPart(Integer likesNo) {
        this.likesNo = likesNo;
        usersLikes=new ArrayList<>();
    }
    public List<String> getUsernamesLikes(){
        List<SiteUser> korisnici=this.usersLikes;
        List<String> imanja=new ArrayList<>();
        for (SiteUser user : korisnici){
            imanja.add(user.getUsername());
            //lista od username da mi vrakja

        }
        return imanja;
    }
}
