package com.example.enetcom_intranet.controller;


import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Teacher;
import com.example.enetcom_intranet.service.ClasseService;
import com.example.enetcom_intranet.service.DepartmentService;
import com.example.enetcom_intranet.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    ClasseService classeService;

    @Autowired
    DepartmentService departmentService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllSTeachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Teacher> getTeacher(@PathVariable Integer id) {
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher) {
        Teacher teacher1 = teacherService.insert(teacher);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("teacher",
                "/api/teacher/" + teacher1.getId());
        //add the teacher to the department teachers list
        departmentService.addToDepartmentTeachersList(teacher.getDepId(), teacher.getId());
        //While creating a teacher we initialise a list of his classes
        //so we need to add the teacher to every classe teachersList
        List<Integer> teacherClasses = teacher.getClassesId();
        for (int i = 0; i < teacherClasses.size(); i++) {
            Classe classe = classeService.getClasseById(teacherClasses.get(i));
            classeService.addToClasseTeachersList(teacher.getId(), classe.getId());
        }
        return new ResponseEntity<>(teacher1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") Integer id, @RequestBody Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Integer id) {
        departmentService.deleteFromDepartmentTeachersList(teacherService.getTeacherById(id).getDepId(), id);
        //we need to delete the teacher from the classes teachers before deleting the teacher
        List<Integer> teacherClasses = teacherService.getTeacherById(id).getClassesId();

        for (int i = 0; i < teacherClasses.size(); i++) {
            Classe classe = classeService.getClasseById(teacherClasses.get(i));
            classeService.deleteFromClasseTeachersList(id, classe.getId());
        }
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}