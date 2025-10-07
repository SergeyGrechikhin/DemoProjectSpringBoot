package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/admin/delete")
public interface AdminDeleteApi {

    @DeleteMapping("/deleteEmployeeById/{IdForDelete}")
    ResponseEntity<ResponceEmployeeDTO> deleteEmployeeById(@PathVariable Integer IdForDelete);

    @DeleteMapping("/deleteDepartmentById/{idForDelete}")
    ResponseEntity<ResponceDepartmentDTO> deleteDepartmentById(@PathVariable Integer idForDelete);

    @DeleteMapping("/deleteTaskById/{idForDelete}")
    ResponseEntity<ResponceTaskDTO> deleteTaskById(@PathVariable Integer idForDelete);



}
