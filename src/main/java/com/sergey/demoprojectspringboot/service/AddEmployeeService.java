package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
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
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Employee with this email already exists");
        }
        if (request.getName() == null || request.getName().isEmpty() || request.getName().equals("")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Employee name is empty");
        }
        if (request.getSurname() == null || request.getSurname().isEmpty() || request.getName().equals("")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Surname is empty");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty() || request.getName().equals("")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email is empty");
        }
        String email = request.getEmail().trim();
        int atCount = email.length() - email.replace("@", "").length();
        if (atCount != 1) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email must contain only one symbol '@'");
        }
        String[] parts = email.split("@");
        if (parts[0].isEmpty()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email must have symbol before '@'");
        }
        if (parts[1].isEmpty() || !parts[1].contains(".")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email must have  '.' after '@'");

        }

        Employee employee = employeeRepository.add(new Employee(request.getName(), request.getSurname(), request.getEmail()));

        return new GlobalResponce<>(HttpStatus.CREATED, ResponceEmployeeDTO.toDTO(employee), "Employee created successfully");

    }
}





