package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.model.Post;
import com.example.enetcom_intranet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Post> getPost(@PathVariable Integer id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Post> saveDepartment(@RequestBody Post post) {
        Post post1 = postService.insert(post);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("post",
                "/api/post/" + post1.getId());
        return new ResponseEntity<>(post1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Post> updateDepartment(@PathVariable("id") Integer id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}