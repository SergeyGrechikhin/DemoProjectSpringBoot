package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor

public class DeleteEmployeeService {

    private EmployeeRepositoryDataBase employeeRepository;

    public ResponceEmployeeDTO deleteEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException(" Employee " + " with " + id + " id " + " not found ");
        }

        Employee employee = employeeOptional.get();
        employeeRepository.delete(employee);

        return ResponceEmployeeDTO.toDTO(employee);

    }
}
