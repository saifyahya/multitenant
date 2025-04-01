package com.example.multitenantdemo.shoppingapp.controller;

import com.example.multitenantdemo.shoppingapp.entity.User;
import com.example.multitenantdemo.shoppingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getAllUsers(){
       return userService.getAllUsers();
    }
}
