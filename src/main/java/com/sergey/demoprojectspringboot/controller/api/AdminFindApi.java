package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/admin/find")
public interface AdminFindApi {
    @GetMapping("/findEmployeeById/{id}")
    ResponseEntity<ResponceEmployeeDTO> findEmployeeById(@PathVariable Integer id);


    @GetMapping("/findEmployeeByName/{name}")
    ResponseEntity<List<ResponceEmployeeDTO>> findEmployeeByName(@PathVariable String name);


    @GetMapping("/findEmployeeBySurname/{surname}")
    ResponseEntity<List<ResponceEmployeeDTO>> findEmployeeBySurname(@PathVariable String surname);


    @GetMapping("/findEmployeeByEmail/{email}")
    ResponseEntity<ResponceEmployeeDTO> findEmployeeByEmail(@PathVariable String email);


    @GetMapping("/findDepartmentAll")
    ResponseEntity<List<ResponceDepartmentDTO>> findDepartmentAll();


    @GetMapping("/findDepartmentById/{id}")
    ResponseEntity<ResponceDepartmentDTO> findDepartmentById(@PathVariable Integer id);


    @GetMapping("/findDepartmentByName/{name}")
    ResponseEntity<ResponceDepartmentDTO> findDepartmentByName(@PathVariable String name);


    @GetMapping("/findEmployeesByDepartmentName/{name}")
    ResponseEntity<List<ResponceEmployeeDTO>> findEmployeesByDepartmentName(@PathVariable String name);

    @GetMapping("/employeesAll")
    ResponseEntity<List<ResponceEmployeeDTO>> findAllEmployees();

    @GetMapping("/employees/allCodes")
    ResponseEntity<List<ConfirmationCode>> findAllEmployeesCodes(@RequestParam String email);

    @GetMapping("/findTaskById/{id}")
    ResponseEntity<ResponceTaskDTO> findTaskById(@PathVariable Integer id);

    @GetMapping("/findTaskByName/{name}")
    ResponseEntity<ResponceTaskDTO> findTaskByName(@PathVariable String name);
}
