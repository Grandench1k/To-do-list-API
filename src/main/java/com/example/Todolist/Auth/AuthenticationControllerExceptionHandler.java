package com.example.Todolist.Auth;

import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

public interface AuthenticationControllerExceptionHandler {

    ResponseEntity<String> handleNotFound(NotFound e);

    ResponseEntity<String> handleNotFound(UsernameNotFoundException e);

    ResponseEntity<String> handleAlreadyDefined(AlreadyDefined e);

    ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e);
}
