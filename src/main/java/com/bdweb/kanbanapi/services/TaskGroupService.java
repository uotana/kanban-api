package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.TaskGroupRequest;
import com.bdweb.kanbanapi.dtos.responses.TaskGroupResponse;
import com.bdweb.kanbanapi.exception.BoardNotFoundException;
import com.bdweb.kanbanapi.exception.TaskGroupNotFoundException;
import com.bdweb.kanbanapi.models.Board;
import com.bdweb.kanbanapi.models.Task;
import com.bdweb.kanbanapi.models.TaskGroup;
import com.bdweb.kanbanapi.repositories.BoardRepository;
import com.bdweb.kanbanapi.repositories.TaskGroupRepository;
import com.bdweb.kanbanapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {

    public final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskRepository taskRepository, TaskGroupRepository taskGroupRepository, BoardRepository boardRepository){
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public TaskGroupResponse save(Long boardId, TaskGroupRequest request) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException("Board with id " + boardId + " not found"));
        TaskGroup group = new TaskGroup();
        group.setName(request.getName());
        group.setBoard(board);
        group.setRegistrationDate(ZonedDateTime.now());
        return taskGroupRepository.save(group).toResponse();
    }

    public List<TaskGroupResponse> findAll() {
        return taskGroupRepository.findAll()
                .stream().map(taskGroup -> taskGroup.toResponse()).collect(Collectors.toList());
    }

    public List<TaskGroupResponse> findAllByBoardId(Long boardId) {
        return taskGroupRepository.findAllByBoardId(boardId)
                .stream().map(taskGroup -> taskGroup.toResponse()).collect(Collectors.toList());
    }

    public TaskGroupResponse findById(Long id) {
        return taskGroupRepository.findById(id).orElseThrow(() -> new TaskGroupNotFoundException("Task group with id " + id + " not found")).toResponse();
    }

    @Transactional
    public TaskGroupResponse update(Long id, TaskGroupRequest request) {
        TaskGroup group = taskGroupRepository.findById(id).orElseThrow(() -> new TaskGroupNotFoundException("Task group with id " + id + " not found"));
        group.setId(group.getId());
        group.setBoard(group.getBoard());
        group.setName(request.getName());
        return taskGroupRepository.save(group).toResponse();
    }

    @Transactional
    public void delete(Long id) {
        taskGroupRepository.findById(id).orElseThrow(() -> new TaskGroupNotFoundException("Task group with id " + id + " not found"));
        taskGroupRepository.deleteById(id);
    }
}
