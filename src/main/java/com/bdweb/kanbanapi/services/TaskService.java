package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.TaskRequest;
import com.bdweb.kanbanapi.dtos.responses.TaskResponse;
import com.bdweb.kanbanapi.exception.TaskGroupNotFoundException;
import com.bdweb.kanbanapi.exception.TaskNotFoundException;
import com.bdweb.kanbanapi.models.Task;
import com.bdweb.kanbanapi.models.TaskGroup;
import com.bdweb.kanbanapi.repositories.TaskGroupRepository;
import com.bdweb.kanbanapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    public final TaskRepository taskRepository;
    public final TaskGroupRepository taskGroupRepository;

    public TaskService(TaskRepository taskRepository, TaskGroupRepository taskGroupRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    @Transactional
    public TaskResponse save(Long taskGroupId, TaskRequest request) {
        TaskGroup taskGroup = taskGroupRepository.findById(taskGroupId)
                .orElseThrow(() -> new TaskGroupNotFoundException("Task group with id " + taskGroupId + " not found"));
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setRegistrationDate(ZonedDateTime.now());
        task.setTaskGroup(taskGroup);
        return taskRepository.save(task).toResponse();
    }

    public List<TaskResponse> findAllByTaskGroupId(Long taskGroupId) {
        taskGroupRepository.findById(taskGroupId)
                .orElseThrow(() -> new TaskGroupNotFoundException("Task group with id " + taskGroupId + " not found"));
        return taskRepository.findAllByTaskGroupId(taskGroupId)
                .stream()
                .map(task -> task.toResponse())
                .collect(Collectors.toList());
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(task -> task.toResponse())
                .collect(Collectors.toList());
    }

    public TaskResponse findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"))
                .toResponse();
    }

    @Transactional
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setTaskGroup(task.getTaskGroup());
        task.setId(task.getId());
        task.setRegistrationDate(task.getRegistrationDate());
        return taskRepository.save(task).toResponse();
    }

    public void delete(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        taskRepository.deleteById(id);
    }
}
