package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class DeleteEmployeeService {

    private EmployeeRepositoryInterface employeeRepository;

    public GlobalResponce<ResponceEmployeeDTO> deleteEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null);
        }

        Employee employee = employeeOptional.get();
        employeeRepository.deleteById(id);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee));

    }
}
