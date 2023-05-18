package com.example.Todolist.Board.Impl;

import com.example.Todolist.Board.Board;
import com.example.Todolist.Board.BoardService;
import com.example.Todolist.Task.Task;
import com.example.Todolist.User.User;
import com.example.Todolist.User.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@SecurityRequirement(name = "Authorization")
public class BoardControllerImpl {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping()
    public List<Board> getAllBoards(Authentication authentication) {
        return boardService.findAllBoardsByUser((User) authentication.getPrincipal());
    }

    @GetMapping("/{UUID}")
    public Board getBoardByUUID(@PathVariable String UUID, Authentication authentication) {
        return boardService.findBoardByUUID(UUID, userService.createUser(authentication));
    }

    @GetMapping("/{UUID}/tasks")
    public List<Task> getAllTasksByBoardUUID(@PathVariable String UUID, Authentication authentication) {
        return boardService.findAllTasksByBoardUUID(UUID, userService.createUser(authentication));
    }

    @PostMapping("/create")
    public Board createBoard(@RequestBody @Valid Board board, Authentication authentication) {
        return boardService.saveBoard(board, userService.createUser(authentication));
    }

    @PutMapping("/{UUID}/update")
    public Board updateBoard(@RequestBody @Valid Board board, @PathVariable String UUID, Authentication authentication) {
        return boardService.updateBoardByUUID(board, UUID, userService.createUser(authentication));
    }

    @DeleteMapping("/{UUID}/delete")
    public Board deleteBoard(@PathVariable String UUID, Authentication authentication) {
        return boardService.deleteBoardByUUID(UUID, userService.createUser(authentication));
    }


}
