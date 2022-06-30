package com.bdweb.kanbanapi.repositories;


import com.bdweb.kanbanapi.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
