package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getDepartments();

    Department getDepartmentById(Integer id);

    Department insert(Department d);

    void updateDepartment(Integer id, Department d);

    void deleteDepartment(Integer id);
}