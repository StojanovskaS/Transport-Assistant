package com.projectweb.transportassistant.service;

import com.projectweb.transportassistant.model.InterestedPart;
import com.projectweb.transportassistant.model.SiteUser;

import java.util.List;

public interface InterestedPartService {
    void addLike(Long interstedpartid,SiteUser user);
    void deleteLike(Long interestedpartid,SiteUser user);
    List<String> likeKorisnici(Long interestedpartid);
}
