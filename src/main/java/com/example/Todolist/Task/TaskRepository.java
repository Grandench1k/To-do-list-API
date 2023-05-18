package com.example.Todolist.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    Task findTaskByUUID(String UUID);

    Task findTaskByName(String name);

    List<Task> findAllTasksByUserID(String userID);

    List<Task> findAllTasksByBoardUUID(String boardUUID);
}
