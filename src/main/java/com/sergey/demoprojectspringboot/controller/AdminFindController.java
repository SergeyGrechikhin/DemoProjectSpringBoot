package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.AdminFindApi;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import com.sergey.demoprojectspringboot.service.findService.FindTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminFindController implements AdminFindApi {
    private final FindEmployeeService findEmployeeService;
    private final FindDepartmentService findDepartmentService;
    private final FindTaskService findTaskService;

    @Override
    public ResponseEntity<ResponseEmployeeDTO> findEmployeeById(Integer id) {
        return ResponseEntity.ok(findEmployeeService.getUserById(id));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findEmployeeByName(String name) {
        return ResponseEntity.ok(findEmployeeService.findByName(name));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findEmployeeBySurname(String surname) {
        return ResponseEntity.ok(findEmployeeService.findBySurname(surname));
    }

    @Override
    public ResponseEntity<ResponseEmployeeDTO> findEmployeeByEmail(String email) {
        return ResponseEntity.ok(findEmployeeService.findByEmail(email));
    }

    @Override
    public ResponseEntity<List<ResponseDepartmentDTO>> findDepartmentAll() {
        return ResponseEntity.ok(findDepartmentService.findAll());
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> findDepartmentById(Integer id) {
        return ResponseEntity.ok(findDepartmentService.findById(id));
    }

    @Override
    public ResponseEntity<ResponseDepartmentDTO> findDepartmentByName(String name) {
        return ResponseEntity.ok(findDepartmentService.findByName(name));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findEmployeesByDepartmentName(String name) {
        return ResponseEntity.ok(findDepartmentService.getEmployeesFromDepartment(name));
    }

    @Override
    public ResponseEntity<List<ResponseEmployeeDTO>> findAllEmployees() {
        return ResponseEntity.ok(findEmployeeService.findAll());
    }

    @Override
    public ResponseEntity<List<ConfirmationCode>> findAllEmployeesCodes(String email) {
        return ResponseEntity.ok(findEmployeeService.findCodesByEmployee(email));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> findTaskById(Integer id) {
        return ResponseEntity.ok(findTaskService.findById(id));
    }

    @Override
    public ResponseEntity<ResponseTaskDTO> findTaskByName(String name) {
        return ResponseEntity.ok(findTaskService.findByName(name));
    }
}
