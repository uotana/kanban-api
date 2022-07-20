package com.bdweb.kanbanapi.repositories;

import com.bdweb.kanbanapi.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTaskGroupId(Long id);
}
