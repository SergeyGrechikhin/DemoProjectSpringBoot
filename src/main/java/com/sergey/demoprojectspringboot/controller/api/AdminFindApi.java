package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
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
    ResponseEntity<ResponseEmployeeDTO> findEmployeeById(@PathVariable Integer id);


    @GetMapping("/findEmployeeByName/{name}")
    ResponseEntity<List<ResponseEmployeeDTO>> findEmployeeByName(@PathVariable String name);


    @GetMapping("/findEmployeeBySurname/{surname}")
    ResponseEntity<List<ResponseEmployeeDTO>> findEmployeeBySurname(@PathVariable String surname);


    @GetMapping("/findEmployeeByEmail/{email}")
    ResponseEntity<ResponseEmployeeDTO> findEmployeeByEmail(@PathVariable String email);


    @GetMapping("/findDepartmentAll")
    ResponseEntity<List<ResponseDepartmentDTO>> findDepartmentAll();


    @GetMapping("/findDepartmentById/{id}")
    ResponseEntity<ResponseDepartmentDTO> findDepartmentById(@PathVariable Integer id);


    @GetMapping("/findDepartmentByName/{name}")
    ResponseEntity<ResponseDepartmentDTO> findDepartmentByName(@PathVariable String name);


    @GetMapping("/findEmployeesByDepartmentName/{name}")
    ResponseEntity<List<ResponseEmployeeDTO>> findEmployeesByDepartmentName(@PathVariable String name);

    @GetMapping("/employeesAll")
    ResponseEntity<List<ResponseEmployeeDTO>> findAllEmployees();

    @GetMapping("/employees/allCodes")
    ResponseEntity<List<ConfirmationCode>> findAllEmployeesCodes(@RequestParam String email);

    @GetMapping("/findTaskById/{id}")
    ResponseEntity<ResponseTaskDTO> findTaskById(@PathVariable Integer id);

    @GetMapping("/findTaskByName/{name}")
    ResponseEntity<ResponseTaskDTO> findTaskByName(@PathVariable String name);
}
