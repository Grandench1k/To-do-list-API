package com.example.Todolist.Board.Impl;

import com.example.Todolist.Board.Board;
import com.example.Todolist.Board.BoardRepository;
import com.example.Todolist.Board.BoardService;
import com.example.Todolist.Exceptions.AlreadyDefined;
import com.example.Todolist.Exceptions.BadRequest;
import com.example.Todolist.Exceptions.NotFound;
import com.example.Todolist.Task.Task;
import com.example.Todolist.Task.TaskRepository;
import com.example.Todolist.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    public final BoardRepository boardRepository;
    public final TaskRepository taskRepository;

    public List<Board> findAllBoardsByUser(User user) throws NotFound {
        List<Board> boardList = boardRepository.findAllBoardsByUserID(user.getId());
        if (boardList.isEmpty()) {
            throw new NotFound("You don't have any boards yet.");
        } else {
            return boardList;
        }
    }

    public Board findBoardByUUID(String UUID, User user) throws NotFound {
        Board board = boardRepository.findBoardByUUID(UUID);
        if (board == null || !user.getId().equals(board.getUserID())) {
            throw new NotFound("A board with this UUID doesn't exist.");
        } else {
            return board;
        }
    }

    public List<Task> findAllTasksByBoardUUID(String UUID, User user) throws NotFound {
        Board board = boardRepository.findBoardByUUID(UUID);
        List<Task> list = taskRepository.findAllTasksByBoardUUID(UUID);
        if (board == null || !user.getId().equals(board.getUserID())) {
            throw new BadRequest("A board with this UUID doesn't exist.");
        } else if (list.isEmpty()) {
            throw new NotFound("A List of Task for board with this UUID doesn't exist.");
        } else {
            return list;
        }
    }

    public Board saveBoard(Board board, User user) throws AlreadyDefined, NotFound {
        Board oldBoard = boardRepository.findBoardByName(board.getName());
        if (oldBoard != null && oldBoard.getUserID().equals(user.getId())) {
            throw new AlreadyDefined("Board with this name already defined.");
        } else {
            board.setUserID(user.getId());
            boardRepository.save(board);
            return board;
        }
    }

    public Board updateBoardByUUID(Board board, String UUID, User user) throws NotFound, AlreadyDefined {
        Board oldBoard = boardRepository.findBoardByUUID(UUID);
        Board newBoard = boardRepository.findBoardByName(board.getName());
        if (UUID == null) {
            throw new BadRequest("Write the UUID of the task you want to updateBoardByUUID.");
        } else if (oldBoard == null) {
            throw new NotFound("A board with this UUID doesn't exist.");
        } else if (newBoard != null) {
            throw new AlreadyDefined("A board with this name already defined.");
        } else if (!oldBoard.getUserID().equals(user.getId())) {
            throw new NotFound("A board with this UUID doesn't exist");
        } else {
            oldBoard.setName(board.getName());
            boardRepository.save(oldBoard);
            return oldBoard;
        }
    }

    public Board deleteBoardByUUID(String UUID, User user) throws NotFound {
        Board boardToDelete = boardRepository.findBoardByUUID(UUID);
        if (UUID == null) {
            throw new BadRequest("Write the UUID of the task you want to deleteTaskByUUID.");
        } else if (boardToDelete == null || !boardToDelete.getUserID().equals(user.getId())) {
            throw new NotFound("A board with this UUID doesn't exist.");
        } else {
            boardRepository.deleteById(UUID);
            return boardToDelete;
        }
    }
}
