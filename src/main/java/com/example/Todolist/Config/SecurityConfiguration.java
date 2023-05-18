package com.example.Todolist.Config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public interface SecurityConfiguration {
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception;
}
