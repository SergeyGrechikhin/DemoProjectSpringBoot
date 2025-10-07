package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.AdminUpdateApi;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDTO;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.service.updateService.UpdateDepartmentService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateEmployeeService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminUpdateController implements AdminUpdateApi {

    private final UpdateEmployeeService updateEmployeeService;
    private final UpdateTaskService updateTaskService;
    private final UpdateDepartmentService updateDepartmentService;

    @Override
    public ResponseEntity<ResponseEmployeeDTO> updateEmployeeNameById(Integer id, String name) {
        return ResponseEntity.ok(updateEmployeeService.updateEmployeeNameById(id, name));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> updateEmployeeSurnameById(Integer id, String surname) {
        return ResponseEntity.ok(updateEmployeeService.updateEmployeeSurnameById(id, surname));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> updateEmployeeEmailById(Integer id, String email) {
        return ResponseEntity.ok(updateEmployeeService.updateEmployeeEmailById(id, email));
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> updateDepartmentNameById(String name, Integer id) {
        return ResponseEntity.ok(updateDepartmentService.updateDepartmentNameById(name, id));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> updateTaskDeadlineById(Integer id, UpdateDeadlineDTO request) {
        return ResponseEntity.ok(updateTaskService.updateTaskDeadlineById(id,request.getDeadline()));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> updateTaskDescriptionById(Integer id, String description) {
        return ResponseEntity.ok(updateTaskService.updateTaskDescriptionById(id, description));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> updateStatusForAdmin(Integer id, UpdateStatusDTO status) {
        return ResponseEntity.ok(updateTaskService.updateTaskStatusByIdForAdmin(id, status));
    }


}
