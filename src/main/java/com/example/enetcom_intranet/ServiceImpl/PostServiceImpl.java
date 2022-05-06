package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Post;
import com.example.enetcom_intranet.model.User;
import com.example.enetcom_intranet.repository.PostRepository;
import com.example.enetcom_intranet.repository.UserRepository;
import com.example.enetcom_intranet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


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

    @Override
    public List<Integer> addToPostsList(Integer uid, Integer pid) {
        User userFromDb = userRepository.findById(uid).get();
        List<Integer> oldList = userFromDb.getPostsId();
        oldList.add(pid);
        userFromDb.setPostsId(oldList);
        userRepository.save(userFromDb);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromPostsList(Integer uid, Integer pid) {
        User userFromDb = userRepository.findById(uid).get();
        List<Integer> oldList = userFromDb.getPostsId();
        oldList.remove(pid);
        userFromDb.setPostsId(oldList);
        userRepository.save(userFromDb);
        return oldList;

    }


}
