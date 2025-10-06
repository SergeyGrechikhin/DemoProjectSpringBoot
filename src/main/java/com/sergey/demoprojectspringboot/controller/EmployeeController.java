package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.EmployeeApi;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {
    private final AddEmployeeService addEmployeeService;


    @Override
    public ResponseEntity<ResponceEmployeeDTO> employeeRegistration(RequestAddEmployeeDTO request) {
        return ResponseEntity.ok(addEmployeeService.employeeRegistration(request));
    }


}
