package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.TaskApi;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.service.addService.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;


    @Override
    public ResponseEntity<ResponseTaskDTO> updateStatusForUser(Integer id, UpdateStatusDTO status) {
        return ResponseEntity.ok(taskService.updateTaskStatusByIdForUser(id, status));
    }

    @Override
    public ResponseEntity<List<ResponseTaskDTO>> getMyTask() {
        return ResponseEntity.ok(taskService.getMyTasks());
    }


}
