package com.example.enetcom_intranet.ServiceImpl;


import com.example.enetcom_intranet.model.Department;
import com.example.enetcom_intranet.repository.ClasseRepository;
import com.example.enetcom_intranet.repository.DepartmentRepository;
import com.example.enetcom_intranet.repository.UserRepository;
import com.example.enetcom_intranet.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClasseRepository classeRepository;


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

    @Override
    public List<Integer> addToDepartmentClassesList(Integer did, Integer cid) {
        Department department = departmentRepository.findById(did).get();
        List<Integer> oldList = department.getClassesId();
        oldList.add(cid);
        department.setClassesId(oldList);
        departmentRepository.save(department);
        return oldList;

    }

    @Override
    public List<Integer> deleteFromDepartmentClassesList(Integer did, Integer cid) {
        Department department = departmentRepository.findById(did).get();
        List<Integer> oldList = department.getClassesId();
        oldList.remove(cid);
        department.setClassesId(oldList);
        departmentRepository.save(department);
        return oldList;

    }

    @Override
    public List<Integer> addToDepartmentStudentsList(Integer did, Integer sid) {
        Department department = departmentRepository.findById(did).get();
        List<Integer> oldList = department.getStudentsId();
        oldList.add(sid);
        department.setStudentsId(oldList);
        departmentRepository.save(department);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromDepartmentStudentsList(Integer did, Integer sid) {
        if (departmentRepository.findById(did).isPresent()) {
            Department department = departmentRepository.findById(did).get();
            List<Integer> oldList = department.getStudentsId();
            oldList.remove(sid);
            department.setStudentsId(oldList);
            departmentRepository.save(department);
            return oldList;
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public List<Integer> addToDepartmentTeachersList(Integer did, Integer tid) {
        Department department = departmentRepository.findById(did).get();
        List<Integer> oldList = department.getTeachersId();
        oldList.add(tid);
        department.setTeachersId(oldList);
        departmentRepository.save(department);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromDepartmentTeachersList(Integer did, Integer tid) {
        if (departmentRepository.findById(did).isPresent()) {
            Department department = departmentRepository.findById(did).get();
            List<Integer> oldList = department.getTeachersId();
            oldList.remove(tid);
            department.setTeachersId(oldList);
            departmentRepository.save(department);
            return oldList;
        } else {

            return Collections.emptyList();
        }
    }


}
