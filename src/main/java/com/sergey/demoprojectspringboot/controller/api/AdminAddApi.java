package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/add")
public interface AdminAddApi {


    @PostMapping("/addNewDepartment")
    ResponseEntity<ResponseDepartmentDTO> addNewDepartment(@RequestBody RequestCreateDepartmentDTO request);


    @GetMapping("/addEmployeeToAnotherDepartment/{departmentId}/{employeeId}")
    ResponseEntity<ResponseEmployeeDTO> addEmployeeToAnotherDepartment(@PathVariable Integer departmentId, @PathVariable Integer employeeId);

    @PostMapping("/addTask")
    ResponseEntity<ResponseTaskDTO> addTask(@RequestBody RequestTaskDTO request);


}
