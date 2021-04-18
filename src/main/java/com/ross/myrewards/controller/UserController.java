package com.ross.myrewards.controller;

import com.ross.myrewards.model.User;
import com.ross.myrewards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("get-all")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
