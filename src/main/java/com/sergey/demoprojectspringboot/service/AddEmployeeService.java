package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddEmployeeService {
    private EmployeeRepository employeeRepository;

    public GlobalResponce<ResponceEmployeeDTO> createEmployee(RequestAddEmployeeDTO request) {
        Optional<Employee> isEmailTry = employeeRepository.findByEmail(request.getEmail());
        if (isEmailTry.isPresent()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT,null," Employee with this email already exists");
        }


        Employee employee = employeeRepository.add(new Employee(request.getName(), request.getSurname(), request.getEmail()));

        return new GlobalResponce<>(HttpStatus.CREATED, ResponceEmployeeDTO.toDTO(employee),"Employee created successfully");

    }
}
