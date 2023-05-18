package com.example.Todolist.Exceptions;


public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
