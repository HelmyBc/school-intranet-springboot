package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.User;
import com.example.enetcom_intranet.repository.UserRepository;
import com.example.enetcom_intranet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
//@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void updateUser(Integer id, User u) {
        User userFromDb = userRepository.findById(id).get();

        userFromDb.setCin(u.getCin());
        userFromDb.setFirstName(u.getFirstName());
        userFromDb.setLastName(u.getLastName());
        userFromDb.setEmail(u.getEmail());
        userFromDb.setPassword(u.getPassword());
        userFromDb.setPhone(u.getPhone());
        userFromDb.setImageUrl(u.getImageUrl());
        userFromDb.setPostsId(u.getPostsId());

        System.out.println(userFromDb);
        userRepository.save(userFromDb);
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}