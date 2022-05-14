package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.ResponseData;
import com.example.enetcom_intranet.model.*;
import com.example.enetcom_intranet.repository.UserRepository;
import com.example.enetcom_intranet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ClasseService classeService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;


    @PostMapping("/login")
    public ResponseEntity<User> Login(@RequestBody User user) {

        List<User> users = userService.getUsers();
        for (int i = 0; i < users.size(); i++) {
            User userFromDb = users.get(i);
            if (userFromDb.getEmail().equals(user.getEmail()) && userFromDb.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<>(userFromDb, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //The function receives a GET request, processes it and gives back a list of Student as a response.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}/classes")
    public ResponseEntity<List<Classe>> getAllClasses(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        List<Classe> allClasses = classeService.getClasses();
        List<Classe> classes = new java.util.ArrayList<>(Collections.emptyList());
        if (Objects.equals(user.getUserType(), "Student")) {
            Student student = studentService.getStudentById(id);
            Classe classe = classeService.getClasseById(student.getClasseId());

            classes.add(classe);

            return new ResponseEntity<>(classes, HttpStatus.OK);
        } else if (Objects.equals(user.getUserType(), "Teacher")) {
            List<Integer> classesIds = teacherService.getTeacherById(id).getClassesId();

            for (int i = 0; i < allClasses.size(); i++) {
                if (classesIds.contains(allClasses.get(i).getId())) {
                    classes.add(allClasses.get(i));
                }
            }
            return new ResponseEntity<>(classes, HttpStatus.OK);
        }
        return null;
    }

    //FOR TEACHERS
    @GetMapping("/{id}/classes/{id1}/subjects")
    public ResponseEntity<List<Subject>> getAllClasseSubjects(@PathVariable Integer id, @PathVariable Integer id1) {
        User user = userService.getUserById(id);
        List<Subject> newTeacherClasseSubjects = new java.util.ArrayList<>(Collections.emptyList());
//
        //if (Objects.equals(user.getUserType(), "Teacher")) {
        Teacher teacher = teacherService.getTeacherById(id);
        List<Integer> teacherSubjects = teacher.getSubjectsId();

        for (int i = 0; i < teacherSubjects.size(); i++) {
            Subject subjectI = subjectService.getSubjectById(teacherSubjects.get(i));
            if (Objects.equals(subjectI.getClasseId(), id1)) {
                newTeacherClasseSubjects.add(subjectI);
            }
        }
        return new ResponseEntity<>(newTeacherClasseSubjects, HttpStatus.OK);
//        }
//        return null;
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        List<Post> posts = new java.util.ArrayList<>(Collections.emptyList());
        List<Post> allPosts = postService.getPosts();
        List<Integer> postsIds = user.getPostsId();
        for (int i = 0; i < allPosts.size(); i++) {
            if (postsIds.contains(allPosts.get(i).getId())) {
                posts.add(allPosts.get(i));
            }
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //For students
    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        List<Subject> allSubjects = subjectService.getSubjects();
        List<Subject> subjects = new java.util.ArrayList<>(Collections.emptyList());
        if (Objects.equals(user.getUserType(), "Student")) {
            Student student = studentService.getStudentById(id);
            Classe classe = classeService.getClasseById(student.getClasseId());
            List<Integer> subjectsIds = classe.getSubjectsId();


            for (int i = 0; i < allSubjects.size(); i++) {
                if (subjectsIds.contains(allSubjects.get(i).getId())) {
                    subjects.add(allSubjects.get(i));
                }
            }
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        }

        return null;
    }

    //The function receives a GET request, processes it, and gives back a list of student as a response.
    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //FOR STUDENTS
    @GetMapping({"/{id}/classe"})
    public ResponseEntity<Classe> getUserClasse(@PathVariable Integer id) {

        if (Objects.equals(userService.getUserById(id).getUserType(), "Student")) {
            Student user = studentService.getStudentById(id);
            Classe classe = classeService.getClasseById(user.getClasseId());
            return new ResponseEntity<>(classe, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/{id}/postsIds"})
    public ResponseEntity<List<Integer>> getUserPostsIds(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id).getPostsId(), HttpStatus.OK);
    }

    //The function receives a POST request, processes it, creates a new Student and saves it to the database, and returns a resource link to the created student.
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService.insert(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user",
                "/api/user/" + user1.getId());
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Student with the specified Id and returns the updated student
    @PutMapping({"/{id}"})
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //The function receives a DELETE request, deletes the Student with the specified Id.
    @DeleteMapping({"/{id}"})
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}