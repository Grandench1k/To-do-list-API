package com.example.Todolist.Board;

import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.NotFound;
import com.example.Todolist.Task.Task;
import com.example.Todolist.User.User;

import java.util.List;

public interface BoardService {
    List<Board> findAllBoardsByUser(User user) throws NotFound;

    Board findBoardByUUID(String UUID, User user) throws NotFound;

    List<Task> findAllTasksByBoardUUID(String UUID, User user) throws NotFound;

    Board saveBoard(Board board, User user) throws AlreadyDefined, NotFound;

    Board updateBoardByUUID(Board board, String UUID, User user) throws NotFound, AlreadyDefined;

    Board deleteBoardByUUID(String UUID, User user) throws NotFound;
}
