package com.example.enetcom_intranet.ServiceImpl;


import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Student;
import com.example.enetcom_intranet.repository.ClasseRepository;
import com.example.enetcom_intranet.repository.StudentRepository;
import com.example.enetcom_intranet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClasseRepository classeRepository;


    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student insert(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(Integer id, Student s) {
        Student studentFromDb = studentRepository.findById(id).get();
        System.out.println(studentFromDb.toString());
        studentFromDb.setCin(s.getCin());
        studentFromDb.setName(s.getName());
        studentFromDb.setEmail(s.getEmail());
        studentFromDb.setPhone(s.getPhone());
        studentFromDb.setDepId(s.getDepId());
        studentFromDb.setClasseId(s.getClasseId());
        studentFromDb.setImageUrl(s.getImageUrl());
        studentFromDb.setPostsId(s.getPostsId());
        studentRepository.save(studentFromDb);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }


}