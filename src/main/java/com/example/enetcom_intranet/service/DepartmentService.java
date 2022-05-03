package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentById(Integer id);

    Department insert(Department d);

    void updateDepartment(Integer id, Department d);

    void deleteDepartment(Integer id);

    List<Integer> addToDepartmentClassesList(Integer did, Integer cid);

    List<Integer> deleteFromDepartmentClassesList(Integer did, Integer cid);

    List<Integer> addToDepartmentStudentsList(Integer did, Integer sid);

    List<Integer> deleteFromDepartmentStudentsList(Integer did, Integer sid);

    List<Integer> addToDepartmentTeachersList(Integer did, Integer tid);

    List<Integer> deleteFromDepartmentTeachersList(Integer did, Integer tid);

}