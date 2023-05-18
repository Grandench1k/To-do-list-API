package com.example.Todolist.Task.Impl;

import com.example.Todolist.Board.Board;
import com.example.Todolist.Board.BoardRepository;
import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.BadRequest;
import com.example.Todolist.Exceptions.NotFound;
import com.example.Todolist.Task.Task;
import com.example.Todolist.Task.TaskRepository;
import com.example.Todolist.Task.TaskService;
import com.example.Todolist.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;

    public List<Task> findAllTasksByUser(User user) throws NotFound {
        List<Task> task = taskRepository.findAllTasksByUserID(user.getId());
        if (task.isEmpty()) {
            throw new NotFound("You don't have any tasks yet.");
        } else {
            return task;
        }
    }

    public Task findTaskByUUID(String UUID, User user) throws NotFound {
        Task task = taskRepository.findTaskByUUID(UUID);
        if (task == null || !user.getId().equals(task.getUserID())) {
            throw new NotFound("A task with this UUID doesn't exist.");
        } else {
            return task;
        }

    }

    public Task saveTask(Task task, User user) throws AlreadyDefined, NotFound {
        Task oldTask = taskRepository.findTaskByName(task.getName());
        Board board = boardRepository.findBoardByUUID(task.getBoardUUID());
        if (board == null || !board.getUserID().equals(user.getId())) {
            throw new NotFound("A board with this UUID doesn't exist.");
        } else if (oldTask != null && oldTask.getUserID().equals(user.getId())) {
            throw new AlreadyDefined("A task with this name is already defined.");
        } else {
            task.setUserID(user.getId());
            taskRepository.save(task);
            return task;
        }
    }

    public Task updateTaskByUUID(Task task, String UUID, User user) throws NotFound, AlreadyDefined {
        Task oldTask = taskRepository.findTaskByUUID(UUID);
        Task newTask = taskRepository.findTaskByName(task.getName());
        if (UUID == null) {
            throw new BadRequest("Write the UUID of the task you want to updateBoardByUUID.");
        } else if (oldTask == null) {
            throw new NotFound("A task with this UUID doesn't exist.");
        } else if (!oldTask.getUserID().equals(user.getId())) {
            throw new AlreadyDefined("A task with this UUID doesn't exist.");
        } else if (newTask != null) {
            throw new AlreadyDefined("A task with this name is already defined.");
        } else {
            oldTask.setName(task.getName());
            taskRepository.save(oldTask);
            return oldTask;
        }
    }

    public Task deleteTaskByUUID(String UUID, User user) throws NotFound {
        Task taskToDelete = taskRepository.findTaskByUUID(UUID);
        if (UUID == null) {
            throw new NotFound("Write the UUID of the task you want to deleteTaskByUUID.");
        } else if (taskToDelete == null || !taskToDelete.getUserID().equals(user.getId())) {
            throw new NotFound("A task with this UUID doesn't exist.");
        } else {
            taskRepository.deleteById(UUID);
            return taskToDelete;
        }
    }
}
