package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.model.Feature;
import com.example.enetcom_intranet.model.Post;
import com.example.enetcom_intranet.service.FeatureService;
import com.example.enetcom_intranet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    FeatureService featureService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Feature>> getAllFeatures() {
        List<Feature> features = featureService.getFeatures();
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Feature> getFeature(@PathVariable Integer id) {
        return new ResponseEntity<>(featureService.getFeatureById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Feature> saveFeature(@RequestBody Feature feature) {
        Feature feature1 = featureService.insert(feature);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("feature",
                "/api/feature/" + feature1.getId());
        return new ResponseEntity<>(feature1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Feature> updateFeature(@PathVariable("id") Integer id, @RequestBody Feature feature) {
        featureService.updateFeature(id, feature);
        return new ResponseEntity<>(featureService.getFeatureById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Feature> deleteFeature(@PathVariable("id") Integer id) {
        featureService.deleteFeature(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}