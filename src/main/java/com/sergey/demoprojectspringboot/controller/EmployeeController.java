package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.service.*;
import lombok.RequiredArgsConstructor;

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
       GlobalResponce<ResponceEmployeeDTO> response = addEmployeeService.createEmployee(request);
       return new ResponseEntity(response.getObject(), response.getStatus());
   }

   @GetMapping
    public ResponseEntity<List<ResponceEmployeeDTO>> findAll(){
       GlobalResponce<List<ResponceEmployeeDTO>> responseList = findEmployeeService.findAll();
       return new ResponseEntity(responseList.getObject(), responseList.getStatus());
   }

   @GetMapping("/{id}")
    public ResponseEntity<ResponceEmployeeDTO> findById(@PathVariable Integer id){
       GlobalResponce<ResponceEmployeeDTO> response = findEmployeeService.findById(id);
       return new ResponseEntity(response.getObject(), response.getStatus());
   }

   @GetMapping("/name/{name}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findByName(@PathVariable String name){
       GlobalResponce<List<ResponceEmployeeDTO>> responce = findEmployeeService.findByName(name);
       return new ResponseEntity<>(responce.getObject(), responce.getStatus());
   }

   @GetMapping("/surname/{surname}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findBySurname(@PathVariable String surname){
       GlobalResponce<List<ResponceEmployeeDTO>> responce = findEmployeeService.findBySurname(surname);
       return new ResponseEntity<>(responce.getObject(), responce.getStatus());
   }

   @GetMapping("/email/{email}")
       public ResponseEntity<ResponceEmployeeDTO> findByEmail(@PathVariable String email){
       GlobalResponce<ResponceEmployeeDTO> response = findEmployeeService.findByEmail(email);
       return new ResponseEntity(response.getObject(), response.getStatus());
   }

   @DeleteMapping("/{IdForDelete}")
    public ResponseEntity<ResponceEmployeeDTO> deleteById(@PathVariable Integer IdForDelete){
       GlobalResponce<ResponceEmployeeDTO> responce = deleteEmployeeService.deleteEmployeeById(IdForDelete);
       return new ResponseEntity<>(responce.getObject(), responce.getStatus());
   }

   @PutMapping ("/updateName/{id}/{name}")
    public ResponseEntity<ResponceEmployeeDTO> updateName(@PathVariable Integer id, @PathVariable String name){
       GlobalResponce<ResponceEmployeeDTO> responce = updateEmployeeService.updateEmployeeNameById(id, name);
       return new ResponseEntity(responce.getObject(), responce.getStatus());
   }

   @PutMapping ("/updateSurname/{id}/{surname}")
    public ResponseEntity<ResponceEmployeeDTO> updateSurname(@PathVariable Integer id, @PathVariable String surname){
       GlobalResponce<ResponceEmployeeDTO> responce = updateEmployeeService.updateEmployeeSurnameById(id, surname);
       return new ResponseEntity(responce.getObject(), responce.getStatus());
   }

    @PutMapping ("/updateEmail/{id}/{email}")
    public ResponseEntity<ResponceEmployeeDTO> updateEmail(@PathVariable Integer id, @PathVariable String email){
       GlobalResponce<ResponceEmployeeDTO> responce = updateEmployeeService.updateEmployeeEmailById(id,email);
       return new ResponseEntity(responce.getObject(), responce.getStatus());
    }




}
