package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.Board;
import com.example.smart_paper.service.AdminMaster.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // Create a new Board
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        // Get the currently logged-in username
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Automatically set the username of the board
        board.setUsername(loggedInUsername);

        Board createdBoard = boardService.saveBoard(board);
        return ResponseEntity.ok(createdBoard);
    }

    // Get all Boards
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // Get a Board by ID
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> board = boardService.getBoardById(id);
        return board.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a Board by ID
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        // Get the currently logged-in username
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Automatically set the username for the update operation
        updatedBoard.setUsername(loggedInUsername);

        Board board = boardService.updateBoard(id, updatedBoard);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Board by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}

