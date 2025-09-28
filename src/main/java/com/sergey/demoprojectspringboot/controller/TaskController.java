package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.*;
import com.sergey.demoprojectspringboot.service.AddTaskService;
import com.sergey.demoprojectspringboot.service.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.FindTaskService;
import com.sergey.demoprojectspringboot.service.UpdateTaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final FindTaskService findDepartmentService;
    private final AddTaskService addTaskService;
    private final UpdateTaskService updateTaskService;


    @PostMapping
    public ResponseEntity<ResponseTaskDTO> add(@RequestBody RequestTaskDTO request){
        return new ResponseEntity<>(addTaskService.createTask(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseTaskDTO updateStatus(@PathVariable Integer id ,@RequestBody UpdateStatusDTO request){
        return updateTaskService.updateTaskStatusById(id, request.getStatus());
    }

}
