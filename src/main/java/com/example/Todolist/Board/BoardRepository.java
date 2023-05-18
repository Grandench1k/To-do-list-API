package com.example.Todolist.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    Board findBoardByName(String name);

    Board findBoardByUUID(String UUID);

    List<Board> findAllBoardsByUserID(String userid);
}