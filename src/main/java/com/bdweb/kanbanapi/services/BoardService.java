package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.BoardRequest;
import com.bdweb.kanbanapi.exception.BoardNotFoundException;
import com.bdweb.kanbanapi.exception.CustomerNotFoundException;
import com.bdweb.kanbanapi.models.Board;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.repositories.BoardRepository;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
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
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
        Board board = new Board();
        board.setName(request.getName());
        board.setCustomer(customer);
        board.setRegistrationDate(ZonedDateTime.now());
        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found"));
    }

    public Board update(Long id, BoardRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found"));
        board.setName(request.getName());
        return boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found"));
        boardRepository.deleteById(id);
    }
}
