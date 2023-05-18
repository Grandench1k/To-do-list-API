package com.example.Todolist.User;

import org.springframework.security.core.Authentication;

public interface UserService {
    User createUser(Authentication authentication);
}
