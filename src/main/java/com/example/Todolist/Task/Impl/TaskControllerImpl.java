package com.example.Todolist.Task.Impl;

import com.example.Todolist.Task.Task;
import com.example.Todolist.Task.TaskController;
import com.example.Todolist.Task.TaskService;
import com.example.Todolist.User.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@SecurityRequirement(name = "Authorization")
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(Authentication authentication) {
        return ResponseEntity.ok(taskService.findAllTasksByUser(userService.createUser(authentication)));
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<Task> getTaskByUUID(@PathVariable String UUID, Authentication authentication) {
        return ResponseEntity.ok(taskService.findTaskByUUID(UUID, userService.createUser(authentication)));
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task, Authentication authentication) {
        return ResponseEntity.ok(taskService.saveTask(task, userService.createUser(authentication)));
    }

    @PutMapping("/{UUID}/update")
    public ResponseEntity<Task> updateTask(@RequestBody @Valid Task task, @PathVariable String UUID, Authentication authentication) {
        return ResponseEntity.ok(taskService.updateTaskByUUID(task, UUID, userService.createUser(authentication)));
    }

    @DeleteMapping("/{UUID}/delete")
    public ResponseEntity<Task> deleteTask(@PathVariable String UUID, Authentication authentication) {
        return ResponseEntity.ok(taskService.deleteTaskByUUID(UUID, userService.createUser(authentication)));
    }
}
