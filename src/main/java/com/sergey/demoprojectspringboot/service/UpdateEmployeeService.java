package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor

public class UpdateEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;
    private ValidationService validationService;


    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeNameById(Integer id, String name) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }
        if (!validationService.LATIN_PATTERN.matcher(name).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Name is invalid");
        }
        Employee employee = employeeForUpdate.get();
        employee.setName(name);

        employeeRepository.save(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeSurnameById(Integer id, String surname) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }
        if (!validationService.LATIN_PATTERN.matcher(surname).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Surname is invalid");
        }
        Employee employee = employeeForUpdate.get();
        employee.setSurname(surname);

        employeeRepository.save(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeEmailById(Integer id, String email) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);

        if (!isEmailAlreadyExist(email)) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Email already exist");
        }

        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }

        if (!validationService.EMAIL_PATTERN.matcher(email).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, "Email is invalid");
        }


        Employee employee = employeeForUpdate.get();
        employee.setEmail(email);

        employeeRepository.save(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }

    private boolean isEmailAlreadyExist(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }


}
