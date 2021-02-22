package com.projectweb.transportassistant.service;

import com.projectweb.transportassistant.model.MyPostsPart;
import com.projectweb.transportassistant.model.Post;

import java.util.List;

public interface MyPostsService {
    List<Post> listAllPostsInMyPostsCard(Long id);
    MyPostsPart getActiveUserPostsCard(String username);
    void addPostToMyPostsCard(Post post,String username);
    void updatePostInMyPostCard(Post post,String username);
    void deletePostFromCard(Long id,String username);
    Post findPostById(Long id,String username);
    Long findPostIdinCard(Post p);
}
