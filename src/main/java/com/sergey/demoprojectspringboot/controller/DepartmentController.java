package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final FindDepartmentService findDepartmentService;
    private final FindEmployeeService findEmployeeService;
    private final AddDepartmentService addDepartmentService;
    private final AddEmployeeToDepartmentService addEmployeeToDepartmentService;
    private final DeleteDepartmentService deleteDepartmentServiceService;
    private final UpdateDepartmentService updateDepartmentService;

    //http://localhost:8080/departments
    @PostMapping
    public ResponseEntity<ResponceDepartmentDTO> add(@RequestBody RequestCreateDepartmentDTO request){
        GlobalResponce<ResponceDepartmentDTO> globalResponce = addDepartmentService.create(request) ;
        return new ResponseEntity(globalResponce.getObject(),globalResponce.getStatus());
    }

   @GetMapping("/set/{departmentId}/{employeeId}")
    public ResponseEntity<ResponceEmployeeDTO> addEmployeeToDepartment(@PathVariable Integer departmentId, @PathVariable Integer employeeId){
       // Optional<Employee> employeeOptional = findEmployeeService.findByIdForService(departmentid);
       // Optional<Department> departmentOptional= findDepartmentService.findDepartmentByIdForService(departmentid);
        GlobalResponce<ResponceEmployeeDTO> addEmployeeToDepartmentResponse = addEmployeeToDepartmentService.addEmployeeToAnotherDepartment(departmentId,employeeId);
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

    @DeleteMapping("/{idForDelete}")
    public ResponseEntity<ResponceDepartmentDTO> deleteById(@PathVariable Integer idForDelete){
        GlobalResponce<ResponceDepartmentDTO> responce = deleteDepartmentServiceService.deleteDepartment(idForDelete);
        return new ResponseEntity(responce.getObject(), responce.getStatus());
    }

    @PutMapping("/name/{name}/{id}")
    public ResponseEntity<ResponceDepartmentDTO> updateName(@PathVariable String name, @PathVariable Integer id){
        GlobalResponce<ResponceDepartmentDTO> responce = updateDepartmentService.updateDepartmentNameById(name,id);
        return new ResponseEntity(responce.getObject(), responce.getStatus());
    }
}
