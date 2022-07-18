package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.BoardRequest;
import com.bdweb.kanbanapi.dtos.responses.BoardResponse;
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
import java.util.stream.Collectors;

@Service
public class BoardService {

    public final BoardRepository boardRepository;
    public final CustomerRepository customerRepository;

    public BoardService(BoardRepository boardRepository, CustomerRepository customerRepository) {
        this.boardRepository = boardRepository;
        this.customerRepository = customerRepository;
    }

    public BoardResponse save(UUID id, BoardRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
        Board board = new Board();
        board.setName(request.getName());
        board.setCustomer(customer);
        board.setRegistrationDate(ZonedDateTime.now());
        return boardRepository.save(board).toResponse();
    }

    public List<BoardResponse> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(board -> board.toResponse()).collect(Collectors.toList());
    }

    public List<BoardResponse> findAllByCustomerId(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + customerId + " not found"));
        return boardRepository.findAllByCustomerId(customerId)
                .stream()
                .map(board -> board.toResponse()).collect(Collectors.toList());
    }

    public BoardResponse findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found")).toResponse();
    }

    public BoardResponse update(Long id, BoardRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found"));
        board.setName(request.getName());
        return boardRepository.save(board).toResponse();
    }

    public void delete(Long id) {
        boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException("Board with id " + id + " not found"));
        boardRepository.deleteById(id);
    }
}
