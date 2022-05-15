package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.model.Admin;
import com.example.enetcom_intranet.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Admin> getAdmin(@PathVariable Integer id) {
        return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.OK);
    }

    @GetMapping({"/{id}/posts"})
    public ResponseEntity<List<Integer>> getAdminPosts(@PathVariable Integer id) {
        return new ResponseEntity<>(adminService.getAdminById(id).getPostsId(), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        Admin admin1 = adminService.insert(admin);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("admin",
                "/api/admin/" + admin1.getId());

        return new ResponseEntity<>(admin1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Integer id, @RequestBody Admin admin) {
        adminService.updateAdmin(id, admin);
        return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Integer id) {

        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}