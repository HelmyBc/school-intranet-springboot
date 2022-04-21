package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Post;
import com.example.enetcom_intranet.repository.PostRepository;
import com.example.enetcom_intranet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;


    @Override
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post insert(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void updatePost(Integer id, Post post) {
        Post postFromDb = postRepository.findById(id).get();
        System.out.println(postFromDb.toString());
        postFromDb.setDescription(post.getDescription());
        postRepository.save(postFromDb);
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

}
