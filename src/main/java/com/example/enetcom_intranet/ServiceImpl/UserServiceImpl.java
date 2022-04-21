package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.User;
import com.example.enetcom_intranet.repository.UserRepository;
import com.example.enetcom_intranet.service.TeacherService;
import com.example.enetcom_intranet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    public List<User> getUsers() {
        List<User> teachers = new ArrayList<>();
        userRepository.findAll().forEach(teachers::add);
        return teachers;
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User insert(User teacher) {
        return userRepository.save(teacher);
    }

    public void updateUser(Integer id, User t) {
        User teacherFromDb = userRepository.findById(id).get();
        System.out.println(teacherFromDb.toString());
        teacherFromDb.setCin(t.getCin());
        teacherFromDb.setName(t.getName());
        teacherFromDb.setEmail(t.getEmail());
        teacherFromDb.setPhone(t.getPhone());
        teacherFromDb.setImageUrl(t.getImageUrl());
        userRepository.save(teacherFromDb);
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}