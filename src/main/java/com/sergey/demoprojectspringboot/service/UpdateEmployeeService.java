package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@AllArgsConstructor

public class UpdateEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;


    public ResponceEmployeeDTO updateEmployeeNameById(Integer id, String name) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setName(name);

        employeeRepository.save(employee);

        return ResponceEmployeeDTO.toDTO(employee);
    }

    public ResponceEmployeeDTO updateEmployeeSurnameById(Integer id, String surname) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setSurname(surname);

        employeeRepository.save(employee);

        return ResponceEmployeeDTO.toDTO(employee);
    }

    public ResponceEmployeeDTO updateEmployeeEmailById(Integer id, String email) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);

        if (!isEmailAlreadyExist(email)) {
            throw new AlreadyExistException("Email already exist");
        }

        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setEmail(email);

        employeeRepository.save(employee);

        return ResponceEmployeeDTO.toDTO(employee);
    }

    private boolean isEmailAlreadyExist(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }


}
