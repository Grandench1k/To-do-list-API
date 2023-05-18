package com.example.Todolist.Board;

import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.BadRequest;
import com.example.Todolist.Exceptions.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

public interface BoardControllerExceptionHandler {
    ResponseEntity<String> handleNotFound(NotFound e);

    ResponseEntity<String> handleAlreadyDefined(AlreadyDefined e);

    ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e);

    ResponseEntity<String> handleBadRequest(BadRequest e);
}
