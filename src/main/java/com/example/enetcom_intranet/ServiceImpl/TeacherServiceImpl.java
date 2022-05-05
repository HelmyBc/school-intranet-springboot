package com.example.enetcom_intranet.ServiceImpl;


import com.example.enetcom_intranet.model.Department;
import com.example.enetcom_intranet.model.Teacher;
import com.example.enetcom_intranet.repository.DepartmentRepository;
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

    @Autowired
    DepartmentRepository departmentRepository;


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
        teacherFromDb.setPassword(t.getPassword());
        teacherFromDb.setPhone(t.getPhone());
        teacherFromDb.setDepId(t.getDepId());
        teacherFromDb.setClassesId(t.getClassesId());
        teacherFromDb.setImageUrl(t.getImageUrl());
        teacherRepository.save(teacherFromDb);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Integer> addToDepartmentTeachersList(Integer tid, Integer cid) {
        Department department = departmentRepository.findById(cid).get();
        List<Integer> oldList = department.getTeachersId();
        oldList.add(tid);
        department.setTeachersId(oldList);
        departmentRepository.save(department);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromDepartmentTeachersList(Integer tid, Integer cid) {
        Department department = departmentRepository.findById(cid).get();
        List<Integer> oldList = department.getTeachersId();
        oldList.remove(tid);
        department.setTeachersId(oldList);
        departmentRepository.save(department);
        return oldList;
    }


}