package com.example.Todolist.Task;

import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.NotFound;
import com.example.Todolist.User.User;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasksByUser(User user) throws NotFound;

    Task findTaskByUUID(String UUID, User user) throws NotFound;

    Task saveTask(Task task, User user) throws AlreadyDefined, NotFound;

    Task updateTaskByUUID(Task task, String UUID, User user) throws NotFound, AlreadyDefined;

    Task deleteTaskByUUID(String UUID, User user) throws NotFound;
}
