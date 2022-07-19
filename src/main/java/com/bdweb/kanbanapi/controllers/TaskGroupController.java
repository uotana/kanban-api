package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.requests.TaskGroupRequest;
import com.bdweb.kanbanapi.dtos.responses.TaskGroupResponse;
import com.bdweb.kanbanapi.services.TaskGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tasks-groups")
public class TaskGroupController {

    private final TaskGroupService service;

    public TaskGroupController(TaskGroupService taskGroupService){
        this.service = taskGroupService;
    }

    @PostMapping("/{board-id}")
    public ResponseEntity<TaskGroupResponse> saveGroup(@PathVariable("board-id") Long boardId,
                                               @RequestBody TaskGroupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(boardId, request));
    }

    @GetMapping
    public ResponseEntity<List<TaskGroupResponse>> findAllGroups(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroupResponse> findGroupById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroupResponse> updateGroup(@PathVariable("id") Long id,
                                                 @RequestBody TaskGroupRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task group deleted successfully.");
    }
}
