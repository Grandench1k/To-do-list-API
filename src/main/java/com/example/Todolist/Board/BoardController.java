package com.example.Todolist.Board;

import com.example.Todolist.Task.Task;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BoardController {
    List<Board> getAllBoards(@Parameter Authentication authentication);

    Board getBoardByUUID(String UUID, Authentication authentication);

    List<Task> getAllTasksByBoardUUID(String UUID, Authentication authentication);

    Board createBoard(Board board, Authentication authentication);

    Board updateBoard(Board board, String UUID, Authentication authentication);

    Board deleteBoard(String UUID, Authentication authentication);
}
