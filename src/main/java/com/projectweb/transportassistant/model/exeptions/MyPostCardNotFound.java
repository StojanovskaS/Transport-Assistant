package com.projectweb.transportassistant.model.exeptions;

public class MyPostCardNotFound extends RuntimeException{

    public MyPostCardNotFound(Long id) {
        super(String.format("User with this id : %s didn't have card for posts! ",id));
    }
}
