package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddEmployeeService {
    private EmployeeRepository employeeRepository;

    public GlobalResponce<ResponceEmployeeDTO> createEmployee(RequestAddEmployeeDTO request) {


        Employee employee = employeeRepository.add(new Employee(request.getName(), request.getSurname()));

        return new GlobalResponce<>(HttpStatus.CREATED, ResponceEmployeeDTO.toDTO(employee));

    }
}
