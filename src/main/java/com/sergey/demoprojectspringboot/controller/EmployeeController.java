package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;

import com.sergey.demoprojectspringboot.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
   private final EmployeeCoreService employeeCoreService;
   private final FindEmployeeService findEmployeeService;

   @PostMapping
    public String add(@RequestBody RequestAddEmployeeDTO request){
       return employeeCoreService.createEmployee(request).toString();
   }

   @GetMapping
    public String findAll(){
       return findEmployeeService.findAll();
   }

   @GetMapping("/{id}")
    public String findById(@PathVariable Integer id){
       return findEmployeeService.findbyId(id);
   }

   @GetMapping("/by-name/{name}")
    public String findByName(@PathVariable String name){
       return findEmployeeService.findbyName(name);
   }

   @GetMapping("/by-surname/{surname}")
    public String findBySurname(@PathVariable String surname){
       return findEmployeeService.findbySurname(surname);
   }


}
