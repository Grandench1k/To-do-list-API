package com.example.Todolist.Exceptions;

public class AlreadyDefined extends RuntimeException {
    public AlreadyDefined(String message) {
        super(message);
    }
}
