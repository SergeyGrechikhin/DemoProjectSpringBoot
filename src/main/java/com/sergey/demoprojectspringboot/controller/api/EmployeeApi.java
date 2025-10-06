package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface EmployeeApi {

    @GetMapping("/{userId}")
    public ResponseEntity<ResponceEmployeeDTO> getEmployeeById(@PathVariable int userId);

    // можно добавить DeleteUser, UpdateUser
    // @PutMapping("/{userId}")

}
