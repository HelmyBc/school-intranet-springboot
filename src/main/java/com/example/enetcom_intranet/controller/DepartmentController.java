package com.example.enetcom_intranet.controller;


import com.example.enetcom_intranet.service.DepartmentService;
import com.example.enetcom_intranet.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Department> getDepartment(@PathVariable Integer id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        Department department1 = departmentService.insert(department);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("department",
                "/api/department/" + department1.getId());
        return new ResponseEntity<>(department1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Integer id, @RequestBody Department department) {
        departmentService.updateDepartment(id, department);
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Integer id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}