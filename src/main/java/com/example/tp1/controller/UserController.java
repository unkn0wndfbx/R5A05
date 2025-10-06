package com.example.tp1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.tp1.dto.UserCreateRequest;
import com.example.tp1.model.User;
import com.example.tp1.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserCreateRequest request) {
        User u = new User();
        u.setUsername(request.getUsername());
        u.setPassword(request.getPassword());
        u.setRole(request.getRole());
        return userService.create(u);
    }

    @GetMapping
    public List<User> list() {
        return userService.list();
    }
}
