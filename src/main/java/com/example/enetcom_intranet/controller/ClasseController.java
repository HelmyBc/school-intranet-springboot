package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.service.ClasseService;
import com.example.enetcom_intranet.service.DepartmentService;
import com.example.enetcom_intranet.service.SubjectService;
import com.example.enetcom_intranet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/classe")
public class ClasseController {
    @Autowired
    ClasseService classeService;


    @Autowired
    DepartmentService departmentService;

    @Autowired
    SubjectService subjectService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses() {
        List<Classe> classes = classeService.getClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Classe> getClasse(@PathVariable Integer id) {
        return new ResponseEntity<>(classeService.getClasseById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Classe> saveClasse(@RequestBody Classe classe) {
        Classe classe1 = classeService.insert(classe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("classe",
                "/api/classe/" + classe1.getId());
        departmentService.addToDepartmentClassesList(classe.getDepId(), classe.getId());


        //While creating a new Classe
        //We need to add the subject to this classe having the same depId and level
        //List<Subject> subjects = subjectService.getSubjects();
//        for (int i = 0; i < subjects.size(); i++) {
//            Subject subjectI = subjects.get(i);
//            List<Integer> subjectDepIds=subjectI.getDepIds();
//            if (subjectDepIds.contains(classe.getDepId()) && classe.getLevel() == subjectI.getLevel()) {
//                List<Integer> subjectsIds = classe.getSubjectsId();
//                subjectsIds.add(subjectI.getId());
//                classe.setSubjectsId(subjectsIds);
//                classeService.updateClasse(classe.getId(), classe);
//            }
//        }

        return new ResponseEntity<>(classe1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Classe> updateClasse(@PathVariable("id") Integer id, @RequestBody Classe classe) {
        classeService.updateClasse(id, classe);
        return new ResponseEntity<>(classeService.getClasseById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Classe> deleteClasse(@PathVariable("id") Integer id) {
        departmentService.deleteFromDepartmentClassesList(classeService.getClasseById(id).getDepId(), id);
        classeService.deleteClasse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}