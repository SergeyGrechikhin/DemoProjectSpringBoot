package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.service.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.AddDepartmentService;
import com.sergey.demoprojectspringboot.service.FindDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final FindDepartmentService findDepartmentService;
    private final AddDepartmentService addDepartmentService;
    private final AddEmployeeToDepartmentService addEmployeeToDepartmentService;

    //http://localhost:8080/departments
    @PostMapping
    public ResponseEntity<ResponceDepartmentDTO> add(@RequestBody RequestCreateDepartmentDTO request){
        GlobalResponce<ResponceDepartmentDTO> globalResponce = addDepartmentService.create(request) ;
        return new ResponseEntity(globalResponce.getObject(),globalResponce.getStatus());
    }

    @GetMapping("/set/{name}/{employeeId}")
    public ResponseEntity<ResponceDepartmentDTO> addEmployeeToDepartment(@PathVariable String name, @PathVariable Integer employeeId){
        GlobalResponce<ResponceDepartmentDTO> addEmployeeToDepartmentResponse = addEmployeeToDepartmentService.addEmployeeToDepartment(name,employeeId);
        return new ResponseEntity(addEmployeeToDepartmentResponse.getObject(),addEmployeeToDepartmentResponse.getStatus());
    }

    @GetMapping
    public ResponseEntity<List<ResponceDepartmentDTO>> findAll(){
        GlobalResponce<List<ResponceDepartmentDTO>> response = findDepartmentService.findAll();
        return new ResponseEntity<>(response.getObject(), response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponceDepartmentDTO> findById(@PathVariable Integer id){
        GlobalResponce<ResponceDepartmentDTO> response = findDepartmentService.findById(id);
        return new ResponseEntity(response.getObject(),response.getStatus());
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<ResponceDepartmentDTO> findByName(@PathVariable String name){
        GlobalResponce<ResponceDepartmentDTO> response = findDepartmentService.findByName(name);
        return new ResponseEntity(response.getObject(), response.getStatus());
    }

    @GetMapping("/employeesFromDepartment/{name}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findEmployeesByDepartmentName(@PathVariable String name){
        GlobalResponce<List<ResponceEmployeeDTO>> responce = findDepartmentService.getEmployeesFromDepartment(name);
        return new ResponseEntity(responce.getObject(), responce.getStatus());
    }
}
