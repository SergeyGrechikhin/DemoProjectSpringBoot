package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.service.FindDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private FindDepartmentService findDepartmentService;

    @GetMapping
    public String findAll(){
        return findDepartmentService.findAllDepartments();
    }


}
