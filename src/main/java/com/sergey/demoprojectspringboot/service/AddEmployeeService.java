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
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class AddEmployeeService {
    private EmployeeRepository employeeRepository;
    private ValidationService validationService;




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
        if (!validationService.LATIN_PATTERN.matcher(request.getSurname()).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Surname is invalid");
        }
        if(!validationService.LATIN_PATTERN.matcher(request.getName()).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Name is invalid");
        }

        if (!validationService.EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email is invalid");
        }


        Employee employee = employeeRepository.add(new Employee(request.getName(), request.getSurname(), request.getEmail()));

        return new GlobalResponce<>(HttpStatus.CREATED, ResponceEmployeeDTO.toDTO(employee), "Employee created successfully");

    }



}







