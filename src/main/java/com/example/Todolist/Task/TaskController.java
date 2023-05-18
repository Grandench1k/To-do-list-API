package com.example.Todolist.Task;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TaskController {
    ResponseEntity<List<Task>> getAllTasks(Authentication authentication);

    ResponseEntity<Task> createTask(Task task, Authentication authentication);

    ResponseEntity<Task> getTaskByUUID(String UUID, Authentication authentication);

    ResponseEntity<Task> updateTask(Task task, String UUID, Authentication authentication);

    ResponseEntity<Task> deleteTask(String UUID, Authentication authentication);
}
