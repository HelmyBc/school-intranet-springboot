package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeachers();

    Teacher getTeacherById(Integer id);

    Teacher insert(Teacher t);

    void updateTeacher(Integer id, Teacher t);

    void deleteTeacher(Integer id);

    List<Integer> addToDepartmentTeachersList(Integer tid, Integer did);

    List<Integer> deleteFromDepartmentTeachersList(Integer tid, Integer cid);
}
