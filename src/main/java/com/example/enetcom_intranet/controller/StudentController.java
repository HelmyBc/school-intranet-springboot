package com.example.enetcom_intranet.controller;


import com.example.enetcom_intranet.model.Student;
import com.example.enetcom_intranet.service.ClasseService;
import com.example.enetcom_intranet.service.DepartmentService;
import com.example.enetcom_intranet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ClasseService classeService;

    @Autowired
    DepartmentService departmentService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping({"/{id}/posts"})
    public ResponseEntity<List<Integer>> getStudentPosts(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.getStudentById(id).getPostsId(), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student student1 = studentService.insert(student);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("student",
                "/api/student/" + student1.getId());
        classeService.addToClasseStudentsList(student.getId(), student.getClasseId());
        departmentService.addToDepartmentStudentsList(student.getDepId(), student.getId());
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        classeService.deleteFromClasseStudentsList(id, studentService.getStudentById(id).getClasseId());
        departmentService.deleteFromDepartmentStudentsList(studentService.getStudentById(id).getDepId(), id);
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //create a statement for deleted posts after deleting the student
    }
}