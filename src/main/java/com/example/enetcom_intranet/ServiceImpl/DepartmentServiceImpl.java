package com.example.enetcom_intranet.ServiceImpl;


import com.example.enetcom_intranet.model.Department;
import com.example.enetcom_intranet.repository.DepartmentRepository;
import com.example.enetcom_intranet.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public Department insert(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(Integer id, Department d) {
        Department departmentFromDb = departmentRepository.findById(id).get();
        System.out.println(departmentFromDb.toString());
        departmentFromDb.setName(d.getName());
        departmentFromDb.setShortName(d.getShortName());
        departmentRepository.save(departmentFromDb);
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

}
