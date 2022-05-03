package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.model.User;
import com.example.enetcom_intranet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<User> saveTeacher(@RequestBody User user) {
        User user1 = userService.insert(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user",
                "/api/user/" + user1.getId());
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<User> updateTeacher(@PathVariable("id") Integer id, @RequestBody User teacher) {
        userService.updateUser(id, teacher);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}