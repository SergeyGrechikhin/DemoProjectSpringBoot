package com.sergey.demoprojectspringboot.controller;


import com.sergey.demoprojectspringboot.controller.api.AdminDeleteApi;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteDepartmentService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteEmployeeService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminDeleteController implements AdminDeleteApi {
    private final DeleteTaskService deleteTaskService;
    private final DeleteEmployeeService deleteEmployeeService;
    private final DeleteDepartmentService deleteDepartmentService;
    @Override
    public ResponseEntity<ResponceEmployeeDTO> deleteEmployeeById(Integer IdForDelete) {
        return ResponseEntity.ok(deleteEmployeeService.deleteEmployeeById(IdForDelete));
    }

    @Override
    public ResponseEntity<ResponceDepartmentDTO> deleteDepartmentById(Integer idForDelete) {
        return ResponseEntity.ok(deleteDepartmentService.deleteDepartment(idForDelete));
    }

    @Override
    public ResponseEntity<ResponceTaskDTO> deleteTaskById(Integer idForDelete) {
        return ResponseEntity.ok(deleteTaskService.deleteTaskById(idForDelete));
    }
}
