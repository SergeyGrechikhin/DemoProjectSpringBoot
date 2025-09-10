package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UpdateEmployeeService {
    private EmployeeRepositoryInterface employeeRepository;


    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeNameById(Integer id, String name) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }
        if (!LATIN_PATTERN.matcher(name).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Name is invalid");
        }
        Employee employee = employeeForUpdate.get();
        employee.setName(name);

        employeeRepository.saveForUpdate(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeSurnameById(Integer id, String surname) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }
        if (!LATIN_PATTERN.matcher(surname).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Surname is invalid");
        }
        Employee employee = employeeForUpdate.get();
        employee.setSurname(surname);

        employeeRepository.saveForUpdate(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeEmailById(Integer id, String email) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);

        if(!isEmailAlreadyExist(email)){
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Email already exist");
        }

        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }
        String emailCheck = email.trim();
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

        Employee employee = employeeForUpdate.get();
        employee.setEmail(email);

        employeeRepository.saveForUpdate(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }

    private boolean isEmailAlreadyExist(String email) {
       return employeeRepository.findByEmail(email).isEmpty();
    }

    private static final Pattern LATIN_PATTERN = Pattern.compile("^[A-Za-z._!-]+$");
}
