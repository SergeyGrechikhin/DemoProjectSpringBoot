package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.AdminUpdateApi;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDTO;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.service.addService.DepartmentService;
import com.sergey.demoprojectspringboot.service.addService.EmployeeService;
import com.sergey.demoprojectspringboot.service.addService.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminUpdateController implements AdminUpdateApi {

    private final EmployeeService employeeService;
    private final TaskService taskService;
    private final DepartmentService departmentService;

    @Override
    public ResponseEntity<ResponseEmployeeDTO> updateEmployeeNameById(Integer id, String name) {
        return ResponseEntity.ok(employeeService.updateEmployeeNameById(id, name));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> updateEmployeeSurnameById(Integer id, String surname) {
        return ResponseEntity.ok(employeeService.updateEmployeeSurnameById(id, surname));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> updateEmployeeEmailById(Integer id, String email) {
        return ResponseEntity.ok(employeeService.updateEmployeeEmailById(id, email));
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> updateDepartmentNameById(String name, Integer id) {
        return ResponseEntity.ok(departmentService.updateDepartmentNameById(name, id));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> updateTaskDeadlineById(Integer id, UpdateDeadlineDTO request) {
        return ResponseEntity.ok(taskService.updateTaskDeadlineById(id,request.getDeadline()));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> updateTaskDescriptionById(Integer id, String description) {
        return ResponseEntity.ok(taskService.updateTaskDescriptionById(id, description));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> updateStatusForAdmin(Integer id, UpdateStatusDTO status) {
        return ResponseEntity.ok(taskService.updateTaskStatusByIdForAdmin(id, status));
    }


}
