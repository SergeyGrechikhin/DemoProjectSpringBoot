package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteDepartmentService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final FindDepartmentService findDepartmentService;

    private final AddDepartmentService addDepartmentService;
    private final AddEmployeeToDepartmentService addEmployeeToDepartmentService;
    private final DeleteDepartmentService deleteDepartmentServiceService;
    private final UpdateDepartmentService updateDepartmentService;

    //http://localhost:8080/departments
    @PostMapping
    public ResponseEntity<ResponceDepartmentDTO> add(@RequestBody RequestCreateDepartmentDTO request){
        return new ResponseEntity<>(addDepartmentService.create(request), HttpStatus.CREATED);
    }

   @GetMapping("/set/{departmentId}/{employeeId}")
    public ResponseEntity<ResponceEmployeeDTO> addEmployeeToDepartment(@PathVariable Integer departmentId, @PathVariable Integer employeeId){

        return new ResponseEntity<>(addEmployeeToDepartmentService.addEmployeeToAnotherDepartment(departmentId,employeeId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponceDepartmentDTO>> findAll(){

        return new ResponseEntity<>(findDepartmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponceDepartmentDTO> findById(@PathVariable Integer id){

        return new ResponseEntity<>(findDepartmentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<ResponceDepartmentDTO> findByName(@PathVariable String name){

        return new ResponseEntity<>(findDepartmentService.findByName(name),HttpStatus.OK);
    }

    @GetMapping("/employeesFromDepartment/{name}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findEmployeesByDepartmentName(@PathVariable String name){

        return new ResponseEntity<>(findDepartmentService.getEmployeesFromDepartment(name),HttpStatus.OK);
    }

    @DeleteMapping("/{idForDelete}")
    public ResponseEntity<ResponceDepartmentDTO> deleteById(@PathVariable Integer idForDelete){

        return new ResponseEntity<>(deleteDepartmentServiceService.deleteDepartment(idForDelete),HttpStatus.OK);
    }

    @PutMapping("/name/{name}/{id}")
    public ResponseEntity<ResponceDepartmentDTO> updateName(@PathVariable String name, @PathVariable Integer id){

        return new ResponseEntity<>(updateDepartmentService.updateDepartmentNameById(name,id),HttpStatus.OK);
    }
}
