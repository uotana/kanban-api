package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.TaskRequest;
import com.bdweb.kanbanapi.models.Task;
import com.bdweb.kanbanapi.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService taskService){
        this.service = taskService;
    }

    @PostMapping("/{customer-id}")
    public ResponseEntity<Task> saveTask(@PathVariable("customer-id") UUID id,
                                         @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(id, request));
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id,
                                                   @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }
}
