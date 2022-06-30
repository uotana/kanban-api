package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.TaskRequest;
import com.bdweb.kanbanapi.models.Board;
import com.bdweb.kanbanapi.models.Task;
import com.bdweb.kanbanapi.repositories.BoardRepository;
import com.bdweb.kanbanapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    public final TaskRepository taskRepository;
    public final BoardRepository boardRepository;

    public TaskService(TaskRepository taskRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Task save(Long id, TaskRequest request) {
        Task task = new Task();
        Optional<Board> boardOptional = boardRepository.findById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setRegistrationDate(ZonedDateTime.now());
        task.setBoard(boardOptional.get());
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Transactional
    public Task update(Long id, TaskRequest request) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setBoard(taskOptional.get().getBoard());
        task.setId(taskOptional.get().getId());
        task.setRegistrationDate(taskOptional.get().getRegistrationDate());
        return taskRepository.save(task);
    }


    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
