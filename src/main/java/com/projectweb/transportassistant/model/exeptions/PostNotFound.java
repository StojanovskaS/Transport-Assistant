package com.projectweb.transportassistant.model.exeptions;

public class PostNotFound extends RuntimeException{
    public PostNotFound() {
        super("Post not found with this id");
    }
}
