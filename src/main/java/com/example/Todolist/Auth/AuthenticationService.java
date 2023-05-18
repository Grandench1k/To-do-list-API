package com.example.Todolist.Auth;

import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.User.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws AlreadyDefined;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws UsernameNotFoundException;

    Role roleCheck(Role roleFromRequest);
}
