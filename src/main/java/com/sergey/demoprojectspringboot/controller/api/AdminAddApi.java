package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin/add")
public interface AdminAddApi {


    @PostMapping("/addNewDepartment")
    ResponseEntity<ResponceDepartmentDTO> addNewDepartment(@RequestBody RequestCreateDepartmentDTO request);


    @GetMapping("/addEmployeeToAnotherDepartment/{departmentId}/{employeeId}")
    ResponseEntity<ResponceEmployeeDTO> addEmployeeToAnotherDepartment(@PathVariable Integer departmentId, @PathVariable Integer employeeId);

    @PostMapping("/addTask")
    ResponseEntity<ResponceTaskDTO> addTask(@RequestBody RequestTaskDTO request);


}
