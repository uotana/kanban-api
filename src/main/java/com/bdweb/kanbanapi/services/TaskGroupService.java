package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.TaskGroupRequest;
import com.bdweb.kanbanapi.models.TaskGroup;
import com.bdweb.kanbanapi.repositories.TaskGroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskGroupService {

    private final TaskGroupRepository repository;
    public TaskGroupService(TaskGroupRepository taskGroupRepository){
        this.repository = taskGroupRepository;
    }

    @Transactional
    public TaskGroup save(TaskGroupRequest request) {
        TaskGroup status = new TaskGroup();
        status.setName(request.getName());
        return repository.save(status);
    }

    public List<TaskGroup> findAll() {
        return repository.findAll();
    }

    public TaskGroup findById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public TaskGroup update(Long id, TaskGroupRequest request) {
        Optional<TaskGroup> statusOptional = repository.findById(id);
        TaskGroup status = new TaskGroup();
        status.setId(statusOptional.get().getId());
        status.setName(request.getName());
        return repository.save(status);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
