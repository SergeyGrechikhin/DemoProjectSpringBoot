package com.sergey.demoprojectspringboot.controller;


import com.sergey.demoprojectspringboot.controller.api.AdminAddApi;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
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
    private final TaskService addTaskService;


    @Override
    public ResponseEntity<ResponseDepartmentDTO> addNewDepartment(RequestCreateDepartmentDTO request) {
        return ResponseEntity.ok(departmentService.create(request));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        return ResponseEntity.ok(employeeService.addEmployeeToAnotherDepartment(departmentId, employeeId));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> addTask(RequestTaskDTO request) {
        return ResponseEntity.ok(addTaskService.createTask(request));
    }


}
