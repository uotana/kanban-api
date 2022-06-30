package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.TaskGroupRequest;
import com.bdweb.kanbanapi.models.TaskGroup;
import com.bdweb.kanbanapi.services.TaskGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tasks-status")
public class TaskGroupController {

    private final TaskGroupService service;

    public TaskGroupController(TaskGroupService taskGroupService){
        this.service = taskGroupService;
    }

    @PostMapping
    public ResponseEntity<TaskGroup> saveTaskStatus(@RequestBody TaskGroupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> findAllTasksStatus(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> findTaskStatusById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskStatus(@PathVariable("id") Long id,
                                                      @RequestBody TaskGroupRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskStatus(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task status deleted successfully.");
    }
}
