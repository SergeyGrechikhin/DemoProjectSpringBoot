package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.service.*;
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

   @GetMapping("/by-name/{name}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findByName(@PathVariable String name){
       GlobalResponce<List<ResponceEmployeeDTO>> responce = findEmployeeService.findByName(name);
       return new ResponseEntity<>(responce.getObject(), responce.getStatus());
   }

   @GetMapping("/by-surname/{surname}")
    public ResponseEntity<List<ResponceEmployeeDTO>> findBySurname(@PathVariable String surname){
       GlobalResponce<List<ResponceEmployeeDTO>> responce = findEmployeeService.findByName(surname);
       return new ResponseEntity<>(responce.getObject(), responce.getStatus());
   }


}
