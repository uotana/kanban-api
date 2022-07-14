package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.requests.BoardRequest;
import com.bdweb.kanbanapi.models.Board;
import com.bdweb.kanbanapi.services.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/boards")
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService boardService){
        this.service = boardService;
    }
    @PostMapping("/{id}")
    public ResponseEntity<Board> saveBoard(@PathVariable("id") UUID id,
                                           @RequestBody BoardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(id, request));
    }

    @GetMapping
    public ResponseEntity<List<Board>> findAllBoards(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> findBoardById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable("id") Long id,
                                                   @RequestBody BoardRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Board deleted successfully.");
    }
}
