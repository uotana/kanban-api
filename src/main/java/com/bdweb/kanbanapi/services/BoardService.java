package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.BoardRequest;
import com.bdweb.kanbanapi.models.Board;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.repositories.BoardRepository;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    public final BoardRepository boardRepository;
    public final CustomerRepository customerRepository;

    public BoardService(BoardRepository boardRepository, CustomerRepository customerRepository) {
        this.boardRepository = boardRepository;
        this.customerRepository = customerRepository;
    }

    public Board save(UUID id, BoardRequest request) {
        Optional<Customer> userOptional = customerRepository.findById(id);
        Board board = new Board();
        board.setName(request.getName());
        board.setCustomer(userOptional.get());
        board.setRegistrationDate(ZonedDateTime.now());
        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).get();
    }

    public Board update(Long id, BoardRequest request) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        Board board = new Board();
        board.setId(boardOptional.get().getId());
        board.setRegistrationDate(boardOptional.get().getRegistrationDate());
        board.setCustomer(boardOptional.get().getCustomer());
        board.setName(request.getName());
        return boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
