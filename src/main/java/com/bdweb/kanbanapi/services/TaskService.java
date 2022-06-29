package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.TaskRequest;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.models.Task;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import com.bdweb.kanbanapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    public final TaskRepository taskRepository;
    public final CustomerRepository customerRepository;

    public TaskService(TaskRepository taskRepository, CustomerRepository customerRepository) {
        this.taskRepository = taskRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Task save(UUID id, TaskRequest request) {
        Task task = new Task();
        Optional<Customer> customerOptional = customerRepository.findById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setRegistrationDate(ZonedDateTime.now());
        task.setCustomer(customerOptional.get());
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
        task.setCustomer(taskOptional.get().getCustomer());
        task.setId(taskOptional.get().getId());
        task.setRegistrationDate(taskOptional.get().getRegistrationDate());
        return taskRepository.save(task);
    }


    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
