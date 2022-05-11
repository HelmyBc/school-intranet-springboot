package com.example.enetcom_intranet.controller;


import com.example.enetcom_intranet.model.Course;
import com.example.enetcom_intranet.model.Post;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.repository.SubjectRepository;
import com.example.enetcom_intranet.service.CourseService;
import com.example.enetcom_intranet.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    SubjectService subjectService;


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<Course> getCourse(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        Course course1 = courseService.insert(course);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("course",
                "/api/course/" + course1.getId());
        Subject subject = subjectService.getSubjectById(course.getSubjectId());
        List<Integer> subjectCourses = subject.getCoursesIds();
        subjectCourses.add(course.getId());
        subject.setCoursesIds(subjectCourses);
        subjectService.updateSubject(subject.getId(), subject);
        courseService.addToCoursesList(course.getSubjectId(), course.getId());
        return new ResponseEntity<>(course1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<Course> updateCourse(@PathVariable("id") Integer id, @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") Integer id) {
        Course course = courseService.getCourseById(id);
        courseService.deleteFromCoursesList(course.getSubjectId(), id);
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}