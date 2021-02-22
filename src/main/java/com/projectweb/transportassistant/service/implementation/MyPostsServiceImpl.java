package com.projectweb.transportassistant.service.implementation;

import com.projectweb.transportassistant.model.MyPostsPart;
import com.projectweb.transportassistant.model.Post;
import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.enumerations.MyPostsCardStatus;
import com.projectweb.transportassistant.model.exeptions.MyPostCardNotFound;
import com.projectweb.transportassistant.model.exeptions.PostNotFound;
import com.projectweb.transportassistant.model.exeptions.UserNotFoundExeption;
import com.projectweb.transportassistant.repository.MyPostsPartRepository;
import com.projectweb.transportassistant.repository.PostRepository;
import com.projectweb.transportassistant.repository.SiteUserRepository;
import com.projectweb.transportassistant.service.MyPostsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class MyPostsServiceImpl implements MyPostsService {
    private final MyPostsPartRepository myPostsPartRepository;
    private final SiteUserRepository userRepository;
    private final PostRepository postRepository;

    public MyPostsServiceImpl(MyPostsPartRepository myPostsPartRepository, SiteUserRepository userRepository, PostRepository postRepository) {
        this.myPostsPartRepository = myPostsPartRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> listAllPostsInMyPostsCard(Long id) {
        if(!this.myPostsPartRepository.findById(id).isPresent()){
            throw new MyPostCardNotFound(id);
        }
        return this.myPostsPartRepository.findById(id).get().getMyposts();
//        return this.getActiveUserPostsCard(username).get().getMyPosts();
    }

    @Override
    @Transactional
    public MyPostsPart getActiveUserPostsCard(String username) {
        SiteUser user=this.userRepository.findByUsername(username).orElseThrow(UserNotFoundExeption::new);
        return this.myPostsPartRepository.findByUserAndStatus(user, MyPostsCardStatus.CREATED).orElseGet(() -> {
            MyPostsPart carticka = new MyPostsPart(user);
            return this.myPostsPartRepository.save(carticka);
        });
    }

    @Override
    public void addPostToMyPostsCard(Post post,String usename) {
        MyPostsPart karticka=this.getActiveUserPostsCard(usename);

        karticka.getMyposts().add(post);
        this.myPostsPartRepository.save(karticka);
    }

    @Override
    public void updatePostInMyPostCard(Post post, String username) {
        MyPostsPart karticka=this.getActiveUserPostsCard(username);
        karticka.getMyposts().removeIf(i->i.getOpis().equals(post.getOpis()) && i.getUser().getUsername().equals(post.getUser().getUsername()));
        karticka.getMyposts().add(post);
        this.myPostsPartRepository.save(karticka);
    }

    @Override
    public void deletePostFromCard(Long id, String username) {
        MyPostsPart karticka=this.getActiveUserPostsCard(username);
        karticka.getMyposts().removeIf(i->i.getId().equals(id));
        this.myPostsPartRepository.save(karticka);
    }

    @Override
    public Post findPostById(Long id,String username) {
        MyPostsPart karticka=this.getActiveUserPostsCard(username);
        return karticka.getMyposts().stream().filter(i->i.getId().equals(id)).findFirst().orElseThrow(PostNotFound::new);
    }

    @Override
    public Long findPostIdinCard(Post p) {
        MyPostsPart karticka=this.getActiveUserPostsCard(p.getUser().getUsername());
        return karticka.getMyposts().stream().filter(i->i.getOpis().equals(p.getOpis()) && i.getUser().getUsername().equals(p.getUser().getUsername())).findFirst().get().getId();
    }
}
