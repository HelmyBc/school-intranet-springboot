package com.example.enetcom_intranet.service;


import com.example.enetcom_intranet.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();

    Student getStudentById(Integer id);

    Student insert(Student s);

    void updateStudent(Integer id, Student s);

    void deleteStudent(Integer id);
}
