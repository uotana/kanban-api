package com.bdweb.kanbanapi.repositories;


import com.bdweb.kanbanapi.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findAllByCustomerId(UUID customerId);
}
