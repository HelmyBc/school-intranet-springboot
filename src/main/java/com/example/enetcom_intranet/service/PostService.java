package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getPosts();

    Post getPostById(Integer id);

    Post insert(Post p);

    void updatePost(Integer id, Post p);

    void deletePost(Integer id);

    List<Integer> addToPostsList(Integer uid, Integer pid);

    List<Integer> deleteFromPostsList(Integer uid, Integer pid);
}