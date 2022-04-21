package com.example.enetcom_intranet.ServiceImpl;


import com.example.enetcom_intranet.model.Teacher;
import com.example.enetcom_intranet.repository.TeacherRepository;
import com.example.enetcom_intranet.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public Teacher insert(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Integer id, Teacher t) {
        Teacher teacherFromDb = teacherRepository.findById(id).get();
        System.out.println(teacherFromDb.toString());
        teacherFromDb.setCin(t.getCin());
        teacherFromDb.setName(t.getName());
        teacherFromDb.setEmail(t.getEmail());
        teacherFromDb.setPhone(t.getPhone());
        teacherFromDb.setImageUrl(t.getImageUrl());
        teacherRepository.save(teacherFromDb);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }
}