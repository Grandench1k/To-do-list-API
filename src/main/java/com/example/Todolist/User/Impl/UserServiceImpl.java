package com.example.Todolist.User.Impl;

import com.example.Todolist.User.User;
import com.example.Todolist.User.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public User createUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
