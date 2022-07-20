package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.requests.TaskRequest;
import com.bdweb.kanbanapi.dtos.responses.TaskResponse;
import com.bdweb.kanbanapi.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("v1/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService taskService){
        this.service = taskService;
    }

    @PostMapping("/{task-group-id}")
    public ResponseEntity<TaskResponse> saveTask(@PathVariable("task-group-id") Long taskGroupId,
                                                 @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(taskGroupId, request));
    }

    @GetMapping("/all/task-group-id/{task-group-id}")
    public ResponseEntity<List<TaskResponse>> findAllTasksByTaskGroupId(@PathVariable("task-group-id") Long taskGroupId){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllByTaskGroupId(taskGroupId));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id,
                                                   @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }
}
