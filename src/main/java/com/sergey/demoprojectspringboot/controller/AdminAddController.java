package com.sergey.demoprojectspringboot.controller;


import com.sergey.demoprojectspringboot.controller.api.AdminAddApi;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.service.addService.AddDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddTaskService;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor

public class AdminAddController implements AdminAddApi {
    private final FindEmployeeService findEmployeeService;


    private final AddDepartmentService addDepartmentService;
    private final AddEmployeeToDepartmentService addEmployeeToDepartmentService;
    private final AddTaskService addTaskService;


    @Override
    public ResponseEntity<ResponseDepartmentDTO> addNewDepartment(RequestCreateDepartmentDTO request) {
        return ResponseEntity.ok(addDepartmentService.create(request));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        return ResponseEntity.ok(addEmployeeToDepartmentService.addEmployeeToAnotherDepartment(departmentId, employeeId));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> addTask(RequestTaskDTO request) {
        return ResponseEntity.ok(addTaskService.createTask(request));
    }


}
