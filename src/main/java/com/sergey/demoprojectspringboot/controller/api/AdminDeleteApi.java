package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/admin/delete")
public interface AdminDeleteApi {

    @DeleteMapping("/deleteEmployeeById/{IdForDelete}")
    ResponseEntity<ResponseEmployeeDTO> deleteEmployeeById(@PathVariable Integer IdForDelete);

    @DeleteMapping("/deleteDepartmentById/{idForDelete}")
    ResponseEntity<ResponseDepartmentDTO> deleteDepartmentById(@PathVariable Integer idForDelete);

    @DeleteMapping("/deleteTaskById/{idForDelete}")
    ResponseEntity<ResponseTaskDTO> deleteTaskById(@PathVariable Integer idForDelete);



}
