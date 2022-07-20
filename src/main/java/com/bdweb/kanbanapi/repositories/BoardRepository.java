package com.bdweb.kanbanapi.repositories;


import com.bdweb.kanbanapi.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByCustomerId(UUID customerId);
}
