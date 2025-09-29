package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDTO;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.service.addService.AddTaskService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteTaskService;
import com.sergey.demoprojectspringboot.service.findService.FindTaskService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateTaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final FindTaskService findDepartmentService;
    private final AddTaskService addTaskService;
    private final UpdateTaskService updateTaskService;
    private final DeleteTaskService deleteTaskService;


    @PostMapping
    public ResponseEntity<ResponceTaskDTO> add(@RequestBody RequestTaskDTO request) {
        return new ResponseEntity<>(addTaskService.createTask(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponceTaskDTO updateStatus(@PathVariable Integer id, @RequestBody UpdateStatusDTO request) {
        return updateTaskService.updateTaskStatusById(id, request.getStatus());
    }

    @PutMapping("/{id}/deadline")
    public ResponceTaskDTO updateDeadline(@PathVariable Integer id, @RequestBody UpdateDeadlineDTO request) {
        return updateTaskService.updateTaskDeadlineById(id, request.getDeadline());
    }

    @PutMapping("/updateDescription/{id}/{description}")
    public ResponseEntity<ResponceTaskDTO> updateDescription(@PathVariable Integer id, @PathVariable String description) {
        return new ResponseEntity<>(updateTaskService.updateTaskDescriptionById(id, description), HttpStatus.OK) ;
    }

    @DeleteMapping("/{IdForDelete}")
    public ResponseEntity<ResponceTaskDTO> deleteById(@PathVariable Integer IdForDelete) {

        return new ResponseEntity<>(deleteTaskService.deleteTaskById(IdForDelete), HttpStatus.OK);
    }
//Второй метод нo возникает ошибка с выводом информации об ошибки
//    @PutMapping("/updateDeadline/{id}/{deadline}")
//    public ResponseEntity<ResponceTaskDTO> updateDeadline(@PathVariable Integer id, @PathVariable LocalDate deadline) {
//
//        return new ResponseEntity<>(updateTaskService.updateTaskDeadlineById(id, deadline), HttpStatus.OK);
//    }
//
//    @PutMapping("/updateStatus/{id}/{status}")
//    public ResponseEntity<ResponceTaskDTO> updateStatus(@PathVariable Integer id, @PathVariable Task.Status status) {
//
//        return new ResponseEntity<>(updateTaskService.updateTaskStatusById(id, status), HttpStatus.OK);
//    }

}
