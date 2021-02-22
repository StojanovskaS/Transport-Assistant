package com.projectweb.transportassistant.service.implementation;

import com.projectweb.transportassistant.model.InterestedPart;
import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.exeptions.InvalidInterstedPartIDfromPostException;
import com.projectweb.transportassistant.repository.InterestedPartRepository;
import com.projectweb.transportassistant.service.InterestedPartService;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestedPartServiceImpl implements InterestedPartService {
    private final InterestedPartRepository interestedPartRepository;

    public InterestedPartServiceImpl(InterestedPartRepository interestedPartRepository) {
        this.interestedPartRepository = interestedPartRepository;
    }

    @Override
    public void addLike(Long interstedpartid, SiteUser user) {
        InterestedPart likePartofPost=this.interestedPartRepository.findById(interstedpartid).orElseThrow(InvalidInterstedPartIDfromPostException::new);
        SiteUser siteUser=null;
        siteUser=likePartofPost.getUsersLikes().stream().filter(i->i.getUsername().equals(user.getUsername())).findFirst().orElse(null);
        if ( siteUser != null){
            //vo listataznaci go ima
            return;
        }
        likePartofPost.getUsersLikes().add(user);
        Integer br=likePartofPost.getLikesNo();
        br+=1;
        likePartofPost.setLikesNo(br);
        interestedPartRepository.save(likePartofPost);
    }

    @Override
    public void deleteLike(Long interestedpartid, SiteUser user) {
        InterestedPart likePartofPost=this.interestedPartRepository.findById(interestedpartid).orElseThrow(InvalidInterstedPartIDfromPostException::new);
        SiteUser siteUser=null;
        siteUser=likePartofPost.getUsersLikes().stream().filter(i->i.getUsername().equals(user.getUsername())).findFirst().orElse(null);
        if ( siteUser == null){
            //vo listataznaci go nema nemoze da se izbrisa ni da se namali brojacot
            return;
        }
        likePartofPost.getUsersLikes().removeIf(i->i.getUsername().equals(user.getUsername()));
        Integer br=likePartofPost.getLikesNo();
        br-=1;
        likePartofPost.setLikesNo(br);
        interestedPartRepository.save(likePartofPost);

    }

    @Override
    public List<String> likeKorisnici(Long interestedpartid) {
        InterestedPart likePartofPost=this.interestedPartRepository.findById(interestedpartid).orElseThrow(InvalidInterstedPartIDfromPostException::new);
        List<SiteUser> korisnici=likePartofPost.getUsersLikes();
        List<String> imanja=new ArrayList<>();
        for (SiteUser user : korisnici){
            imanja.add(user.getUsername());
            //lista od username da mi vrakja

        }
        return imanja;
    }
}
