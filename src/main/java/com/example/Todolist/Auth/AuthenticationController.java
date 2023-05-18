package com.example.Todolist.Auth;

import org.springframework.http.ResponseEntity;

public interface AuthenticationController {
    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
