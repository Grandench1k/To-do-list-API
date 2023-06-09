package com.example.Todolist.Task.Impl;

import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.BadRequest;
import com.example.Todolist.Exceptions.NotFound;
import com.example.Todolist.Task.TaskController;
import com.example.Todolist.Task.TaskControllerExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice(basePackageClasses = TaskController.class)
public class TaskControllerExceptionHandlerImpl implements TaskControllerExceptionHandler {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AlreadyDefined.class)
    public ResponseEntity<String> handleAlreadyDefined(AlreadyDefined e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<String> handleBadRequest(BadRequest e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }

}
