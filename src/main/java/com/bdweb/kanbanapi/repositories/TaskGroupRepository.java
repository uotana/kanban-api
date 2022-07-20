package com.bdweb.kanbanapi.repositories;

import com.bdweb.kanbanapi.models.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
    List<TaskGroup> findAllByBoardId(Long boardId);
}
