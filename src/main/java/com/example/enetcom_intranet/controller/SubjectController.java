package com.example.enetcom_intranet.controller;


import com.example.enetcom_intranet.model.Feature;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.service.FeatureService;
import com.example.enetcom_intranet.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Subject> getSubject(@PathVariable Integer id) {
        return new ResponseEntity<>(subjectService.getSubjectById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject) {
        Subject subject1 = subjectService.insert(subject);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("subject",
                "/api/subject/" + subject1.getId());
        return new ResponseEntity<>(subject1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") Integer id, @RequestBody Subject subject) {
        subjectService.updateSubject(id, subject);
        return new ResponseEntity<>(subjectService.getSubjectById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") Integer id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}