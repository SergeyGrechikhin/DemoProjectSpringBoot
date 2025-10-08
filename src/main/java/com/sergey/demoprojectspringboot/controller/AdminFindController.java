package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.AdminFindApi;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import com.sergey.demoprojectspringboot.service.DepartmentService;
import com.sergey.demoprojectspringboot.service.EmployeeService;
import com.sergey.demoprojectspringboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminFindController implements AdminFindApi {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final TaskService taskService;

    @Override
    public ResponseEntity<ResponseEmployeeDTO> findEmployeeById(Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findEmployeeByName(String name) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findEmployeeBySurname(String surname) {
        return ResponseEntity.ok(employeeService.findBySurname(surname));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> findEmployeeByEmail(String email) {
        return ResponseEntity.ok(employeeService.findByEmail(email));
    }

    @Override
    public ResponseEntity<List<ResponseDepartmentDTO>> findDepartmentAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> findDepartmentById(Integer id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> findDepartmentByName(String name) {
        return ResponseEntity.ok(departmentService.findByName(name));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findEmployeesByDepartmentName(String name) {
        return ResponseEntity.ok(departmentService.getEmployeesFromDepartment(name));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @Override
    public ResponseEntity<List<ConfirmationCode>> findAllEmployeesCodes(String email) {
        return ResponseEntity.ok(employeeService.findCodesByEmployee(email));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> findTaskById(Integer id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> findTaskByName(String name) {
        return ResponseEntity.ok(taskService.findByName(name));
    }
}
