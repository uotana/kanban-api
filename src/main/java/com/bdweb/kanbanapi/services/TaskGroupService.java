package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.TaskGroupRequest;
import com.bdweb.kanbanapi.exception.BoardNotFoundException;
import com.bdweb.kanbanapi.models.Board;
import com.bdweb.kanbanapi.models.TaskGroup;
import com.bdweb.kanbanapi.repositories.BoardRepository;
import com.bdweb.kanbanapi.repositories.TaskGroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, BoardRepository boardRepository){
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public TaskGroup save(Long boardId, TaskGroupRequest request) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException("Board with id " + boardId + " not found"));
        TaskGroup group = new TaskGroup();
        group.setName(request.getName());
        group.setBoard(board);
        return taskGroupRepository.save(group);
    }

    public List<TaskGroup> findAll() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup findById(Long id) {
        return taskGroupRepository.findById(id).get();
    }

    @Transactional
    public TaskGroup update(Long id, TaskGroupRequest request) {
        Optional<TaskGroup> statusOptional = taskGroupRepository.findById(id);
        TaskGroup status = new TaskGroup();
        status.setId(statusOptional.get().getId());
        status.setName(request.getName());
        return taskGroupRepository.save(status);
    }

    @Transactional
    public void delete(Long id) {
        taskGroupRepository.deleteById(id);
    }
}
