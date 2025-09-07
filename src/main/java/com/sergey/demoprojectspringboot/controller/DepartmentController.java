package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.service.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.DepartmentCoreService;
import com.sergey.demoprojectspringboot.service.FindDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final FindDepartmentService findDepartmentService;
    private final DepartmentCoreService departmentCoreService;
    private final AddEmployeeToDepartmentService addEmployeeToDepartmentService;

    //http://localhost:8080/departments
    @PostMapping
    public String add(@RequestBody RequestCreateDepartmentDTO request){
        return departmentCoreService.create(request).toString();
    }

    @PostMapping("/{name}/employees/{employeeId}")
    public String addEmployeeToDepartment(@PathVariable String name, @PathVariable Integer employeeId){
        return addEmployeeToDepartmentService.addEmployeeToDepartment(name, employeeId);
    }

    @GetMapping
    public String findAll(){
        return findDepartmentService.findAll();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id){
        return findDepartmentService.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public String findByName(@PathVariable String name){
        return findDepartmentService.findByName(name);
    }
}
