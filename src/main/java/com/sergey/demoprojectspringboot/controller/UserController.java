package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.UserProfileApi;
import com.sergey.demoprojectspringboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserProfileApi {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<Void> deactivateMe() {
        employeeService.deactivateEmployee();
        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Void> reactivateMe() {
        employeeService.reactivateEmployee();
        return ResponseEntity.noContent().build();
    }
}
