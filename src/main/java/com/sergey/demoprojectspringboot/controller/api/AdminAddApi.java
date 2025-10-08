package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateEmployeeOwnerDto;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateTaskOwnerDto;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDto;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDto;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/add")
public interface AdminAddApi {


    @PostMapping("/addNewDepartment")
    ResponseEntity<ResponseDepartmentDTO> addNewDepartment(@RequestBody RequestCreateDepartmentDto request);


    @GetMapping("/addEmployeeToDepartment")
    ResponseEntity<ResponseEmployeeDTO> addEmployeeDepartment(@RequestBody UpdateEmployeeOwnerDto request);

    @PostMapping("/addTask")
    ResponseEntity<ResponseTaskDTO> addTask(@RequestBody RequestTaskDto request);

    @GetMapping("/addTaskForUser")
    ResponseEntity<ResponseTaskDTO> addTaskForUser(@RequestBody UpdateTaskOwnerDto request);


}
