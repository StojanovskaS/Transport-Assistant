package com.projectweb.transportassistant.repository;

import com.projectweb.transportassistant.model.MyPostsPart;
import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.enumerations.MyPostsCardStatus;
import com.projectweb.transportassistant.model.enumerations.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyPostsPartRepository extends JpaRepository<MyPostsPart,Long> {
    Optional<MyPostsPart> findByUserAndStatus(SiteUser user, MyPostsCardStatus status);

}
