package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.TaskApi;
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
import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController implements TaskApi {

    private final UpdateTaskService updateTaskService;
    private final FindTaskService findTaskService;

    @Override
    public ResponseEntity<ResponceTaskDTO> updateStatusForUser(Integer id, UpdateStatusDTO status) {
        return ResponseEntity.ok(updateTaskService.updateTaskStatusByIdForUser(id, status));
    }

    @Override
    public ResponseEntity<List<ResponceTaskDTO>> getMyTask() {
        return ResponseEntity.ok(findTaskService.getMyTasks());
    }


}
