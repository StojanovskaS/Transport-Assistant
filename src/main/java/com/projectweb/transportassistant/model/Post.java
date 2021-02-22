package com.projectweb.transportassistant.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    SiteUser user;
    String odGrad;
    Float cena;
    String opis;
    GregorianCalendar den;
//    opisot e bidejki html da ne e sozen ke gi ima samo gradovite vo Makedonija ,a na primer moze da e krajna destinacija nekoe selo vo Makedonija
    @ManyToMany
    List<Category> categoryList;
    @OneToOne
    InterestedPart likepart;
//    ke moze eden na primer da si odi od Ohrid do Pehcevo i da minuva niz zapadna,centralna i istocna Makedonija


    public Post(SiteUser user, String odGrad,String opis, Float cena,GregorianCalendar den, List<Category> categoryList,InterestedPart likepart) {
        this.user = user;
        this.opis=opis;
        this.categoryList = categoryList;
        this.odGrad = odGrad;
        this.cena = cena;
        this.den=den;
        this.likepart=likepart;
    }

    public Post() {
    }
    public String getData(){
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormatted = formattedDate.format(den.getTime());
        return dateFormatted;
    }
}
