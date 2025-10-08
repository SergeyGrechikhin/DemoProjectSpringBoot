package com.sergey.demoprojectspringboot.controller;


import com.sergey.demoprojectspringboot.controller.api.AdminDeleteApi;
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
public class AdminDeleteController implements AdminDeleteApi {
    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    @Override
    public ResponseEntity<ResponseEmployeeDTO> deleteEmployeeById(Integer IdForDelete) {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(IdForDelete));
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> deleteDepartmentById(Integer idForDelete) {
        return ResponseEntity.ok(departmentService.deleteDepartment(idForDelete));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> deleteTaskById(Integer idForDelete) {
        return ResponseEntity.ok(taskService.deleteTaskById(idForDelete));
    }
}
