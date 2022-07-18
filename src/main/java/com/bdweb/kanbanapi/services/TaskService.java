package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.TaskRequest;
import com.bdweb.kanbanapi.exception.TaskGroupNotFoundException;
import com.bdweb.kanbanapi.models.Task;
import com.bdweb.kanbanapi.models.TaskGroup;
import com.bdweb.kanbanapi.repositories.TaskGroupRepository;
import com.bdweb.kanbanapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    public final TaskRepository taskRepository;
    public final TaskGroupRepository taskGroupRepository;

    public TaskService(TaskRepository taskRepository, TaskGroupRepository taskGroupRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    @Transactional
    public Task save(Long id, TaskRequest request) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new TaskGroupNotFoundException("Task group with id " + id + " not found"));
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setRegistrationDate(ZonedDateTime.now());
        task.setTaskGroup(taskGroup);
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
        task.setTaskGroup(task.getTaskGroup());
        task.setId(taskOptional.get().getId());
        task.setRegistrationDate(taskOptional.get().getRegistrationDate());
        return taskRepository.save(task);
    }


    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
