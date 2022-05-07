package com.example.enetcom_intranet.controller;


import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Feature;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.repository.ClasseRepository;
import com.example.enetcom_intranet.service.ClasseService;
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

    @Autowired
    ClasseService classeService;


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

    //The function receives a POST request, processes it, creates a new Student and saves it to the database,
    // and returns a resource link to the created student.

    //I need to add this subject to every class having the same dep and level
    @PostMapping
    public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject) {
        Subject subject1 = subjectService.insert(subject);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("subject",
                "/api/subject/" + subject1.getId());


        //While creating a subject having a depId and level
        //We need to add the subject to every classe having the same depId and level
        List<Classe> classes = classeService.getClasses();
        List<Integer> subjectDepIds = subject.getDepIds();
        for (int i = 0; i < classes.size(); i++) {
            Classe classeI = classes.get(i);
            if (subjectDepIds.contains(classeI.getDepId()) && classeI.getLevel() == subject.getLevel()) {
                List<Integer> subjectsIds = classeI.getSubjectsId();
                subjectsIds.add(subject.getId());
                classeI.setSubjectsId(subjectsIds);
                classeService.updateClasse(classeI.getId(), classeI);
            }
        }


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
        List<Classe> classes = classeService.getClasses();
        for (int i = 0; i < classes.size(); i++) {
            Classe classeI = classes.get(i);
            List<Integer> classeISubjects = classeI.getSubjectsId();
            if (classeISubjects.contains(id)) {
                classeISubjects.remove(id);
                classeI.setSubjectsId(classeISubjects);
            }
        }
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}