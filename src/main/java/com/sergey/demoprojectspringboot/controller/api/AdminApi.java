package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
public interface AdminApi {



    @GetMapping("/employees/details")
    ResponseEntity<List<ResponceEmployeeDTO>> findAllEmployees();

    @GetMapping("/employees/allCodes")
    ResponseEntity<List<ConfirmationCode>> findAllEmployeesCodes(@RequestParam String email);

    @PutMapping("/updateName/{id}/{name}")
    ResponseEntity<ResponceEmployeeDTO> updateEmployeeName(@PathVariable Integer id, @PathVariable String name);


    @PutMapping("/updateSurname/{id}/{surname}")
    ResponseEntity<ResponceEmployeeDTO> updateEmployeeSurname(@PathVariable Integer id, @PathVariable String surname);


    @PutMapping("/updateEmail/{id}/{email}")
    ResponseEntity<ResponceEmployeeDTO> updateEmployeeEmail(@PathVariable Integer id, @PathVariable String email);

    @DeleteMapping("/deleteEmployeeById/{IdForDelete}")
    ResponseEntity<ResponceEmployeeDTO> deleteEmployeeById(@PathVariable Integer IdForDelete);


    @GetMapping("/findEmployeeById/{id}")
    ResponseEntity<ResponceEmployeeDTO> findEmployeeById(@PathVariable Integer id);


    @GetMapping("/findEmployeeByName/name/{name}")
    ResponseEntity<List<ResponceEmployeeDTO>> findEmployeeByName(@PathVariable String name);


    @GetMapping("/findEmployeeBySurname/surname/{surname}")
    ResponseEntity<List<ResponceEmployeeDTO>> findEmployeeBySurname(@PathVariable String surname);


    @GetMapping("/findEmployeeByEmail/email/{email}")
    ResponseEntity<ResponceEmployeeDTO> findEmployeeByEmail(@PathVariable String email);

    @DeleteMapping("/deleteDepartmentById/{idForDelete}")
    ResponseEntity<ResponceDepartmentDTO> deleteDepartmentById(@PathVariable Integer idForDelete);


    @PutMapping("/updateDepartmentName/name/{name}/{id}")
    ResponseEntity<ResponceDepartmentDTO> updateDepartmentName(@PathVariable String name, @PathVariable Integer id);

    @GetMapping("/findDepartmentAll/findAllDepartments")
    ResponseEntity<List<ResponceDepartmentDTO>> findDepartmentAll();


    @GetMapping("/findDepartmentById/{id}")
    ResponseEntity<ResponceDepartmentDTO> findDepartmentById(@PathVariable Integer id);


    @GetMapping("/findDepartmentByName/byName/{name}")
    ResponseEntity<ResponceDepartmentDTO> findDepartmentByName(@PathVariable String name);


    @GetMapping("/findEmployeesByDepartmentName/employeesFromDepartment/{name}")
    ResponseEntity<List<ResponceEmployeeDTO>> findEmployeesByDepartmentName(@PathVariable String name);

    @PostMapping("/addNewDepartment")
    ResponseEntity<ResponceDepartmentDTO> addNewDepartment(@RequestBody RequestCreateDepartmentDTO request);


    @GetMapping("/addEmployeeToAnotherDepartment/{departmentId}/{employeeId}")
    ResponseEntity<ResponceEmployeeDTO> addEmployeeToAnotherDepartment(@PathVariable Integer departmentId, @PathVariable Integer employeeId);

    @PostMapping("/addTask")
    ResponseEntity<ResponceTaskDTO> addTask(@RequestBody RequestTaskDTO request);

    @PutMapping("/updateDeadline/{id}/deadline")
    ResponseEntity<ResponceTaskDTO> updateDeadline(@PathVariable Integer id, @RequestBody UpdateDeadlineDTO request);


    @PutMapping("/updateDescription/{id}/{description}")
    ResponseEntity<ResponceTaskDTO> updateDescription(@PathVariable Integer id, @PathVariable String description);


    @DeleteMapping("deleteTaskById/{IdForDelete}")
    ResponseEntity<ResponceTaskDTO> deleteTaskById(@PathVariable Integer IdForDelete);


}
