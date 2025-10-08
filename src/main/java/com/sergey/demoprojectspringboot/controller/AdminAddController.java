package com.sergey.demoprojectspringboot.controller;


import com.sergey.demoprojectspringboot.controller.api.AdminAddApi;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateEmployeeOwnerDto;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateTaskOwnerDto;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDto;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDto;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.service.DepartmentService;
import com.sergey.demoprojectspringboot.service.EmployeeService;
import com.sergey.demoprojectspringboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor

public class AdminAddController implements AdminAddApi {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final TaskService taskService;


    @Override
    public ResponseEntity<ResponseDepartmentDTO> addNewDepartment(RequestCreateDepartmentDto request) {
        return ResponseEntity.ok(departmentService.create(request));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> addEmployeeDepartment(UpdateEmployeeOwnerDto request) {
        return ResponseEntity.ok(employeeService.addEmployeeToDepartment(request));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> addTask(RequestTaskDto request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> addTaskForUser(UpdateTaskOwnerDto request) {
        return ResponseEntity.ok(taskService.addTaskToEmployee(request));
    }


}
