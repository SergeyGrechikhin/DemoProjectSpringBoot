package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteEmployeeService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
   private final AddEmployeeService addEmployeeService;
   private final FindEmployeeService findEmployeeService;
   private final DeleteEmployeeService deleteEmployeeService;
   private final UpdateEmployeeService updateEmployeeService;

   @PostMapping
    public ResponseEntity<ResponceEmployeeDTO> add(@RequestBody RequestAddEmployeeDTO request){
       return new ResponseEntity<>(addEmployeeService.createEmployee(request), HttpStatus.CREATED);
   }

   @GetMapping
    public ResponseEntity<List<ResponceEmployeeDTO>> findAll(){

       return new ResponseEntity<>(findEmployeeService.findAll(),HttpStatus.OK);
   }

   @GetMapping("/{id}")
    public ResponseEntity<ResponceEmployeeDTO> findById(@PathVariable Integer id){

       return new ResponseEntity<>(findEmployeeService.findById(id),HttpStatus.OK);
   }

   @GetMapping("/name/{name}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findByName(@PathVariable String name){

       return new ResponseEntity<>(findEmployeeService.findByName(name),HttpStatus.OK);
   }

   @GetMapping("/surname/{surname}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findBySurname(@PathVariable String surname){

       return new ResponseEntity<>(findEmployeeService.findBySurname(surname),HttpStatus.OK);
   }

   @GetMapping("/email/{email}")
       public ResponseEntity<ResponceEmployeeDTO> findByEmail(@PathVariable String email){

       return new ResponseEntity<>(findEmployeeService.findByEmail(email),HttpStatus.OK);
   }

   @DeleteMapping("/{IdForDelete}")
    public ResponseEntity<ResponceEmployeeDTO> deleteById(@PathVariable Integer IdForDelete){

       return new ResponseEntity<>(deleteEmployeeService.deleteEmployeeById(IdForDelete),HttpStatus.OK);
   }

   @PutMapping ("/updateName/{id}/{name}")
    public ResponseEntity<ResponceEmployeeDTO> updateName(@PathVariable Integer id, @PathVariable String name){

       return new ResponseEntity<>(updateEmployeeService.updateEmployeeNameById(id, name),HttpStatus.OK);
   }

   @PutMapping ("/updateSurname/{id}/{surname}")
    public ResponseEntity<ResponceEmployeeDTO> updateSurname(@PathVariable Integer id, @PathVariable String surname){

       return new ResponseEntity<>(updateEmployeeService.updateEmployeeSurnameById(id, surname),HttpStatus.OK);
   }

    @PutMapping ("/updateEmail/{id}/{email}")
    public ResponseEntity<ResponceEmployeeDTO> updateEmail(@PathVariable Integer id, @PathVariable String email){

       return new ResponseEntity<>(updateEmployeeService.updateEmployeeEmailById(id,email),HttpStatus.OK);
    }




}
