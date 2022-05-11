package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.model.Course;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.model.Td;
import com.example.enetcom_intranet.service.SubjectService;
import com.example.enetcom_intranet.service.TdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/td")
public class TdController {
    @Autowired
    TdService tdService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Td>> getAllTds() {
        List<Td> tds = tdService.getTds();
        return new ResponseEntity<>(tds, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Td> getTd(@PathVariable Integer id) {
        return new ResponseEntity<>(tdService.getTdById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Td> saveTd(@RequestBody Td td) {
        Td td1 = tdService.insert(td);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("course",
                "/api/course/" + td1.getId());

        tdService.addToTdsList(td.getSubjectId(), td.getId());
        return new ResponseEntity<>(td1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Td> updateTd(@PathVariable("id") Integer id, @RequestBody Td td) {
        tdService.updateTd(id, td);
        return new ResponseEntity<>(tdService.getTdById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Td> deleteTd(@PathVariable("id") Integer id) {
        Td td = tdService.getTdById(id);
        tdService.deleteFromTdsList(td.getSubjectId(), id);
        tdService.deleteTd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}