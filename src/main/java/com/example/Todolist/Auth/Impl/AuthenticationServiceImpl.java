package com.example.Todolist.Auth.Impl;

import com.example.Todolist.Auth.AuthenticationRequest;
import com.example.Todolist.Auth.AuthenticationResponse;
import com.example.Todolist.Auth.AuthenticationService;
import com.example.Todolist.Auth.RegisterRequest;
import com.example.Todolist.Config.JwtService;
import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.User.Role;
import com.example.Todolist.User.User;
import com.example.Todolist.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.Todolist.User.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws AlreadyDefined {
        User user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).role(roleCheck(request.getRole())).password(passwordEncoder.encode(request.getPassword())).build();
        if (userRepository.findUserByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } else {
            throw new AlreadyDefined("This user already exist.");
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UsernameNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public Role roleCheck(Role roleFromRequest) {
        return Objects.requireNonNullElse(roleFromRequest, USER);
    }
}
