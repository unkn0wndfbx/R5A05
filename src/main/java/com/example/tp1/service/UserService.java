package com.example.tp1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tp1.model.User;
import com.example.tp1.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
