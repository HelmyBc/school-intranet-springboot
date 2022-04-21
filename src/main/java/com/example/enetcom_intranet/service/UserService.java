package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Teacher;
import com.example.enetcom_intranet.model.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    User getUserById(Integer id);

    User insert(User u);

    void updateUser(Integer id, User u);

    void deleteUser(Integer id);
}
