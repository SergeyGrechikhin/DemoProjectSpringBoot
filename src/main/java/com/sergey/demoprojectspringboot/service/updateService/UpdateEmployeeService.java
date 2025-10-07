package com.sergey.demoprojectspringboot.service.updateService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.EmployeeConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@AllArgsConstructor

public class UpdateEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;
    private EmployeeConverter converter;


    public ResponseEmployeeDTO updateEmployeeNameById(Integer id, String name) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setName(name);

        employeeRepository.save(employee);

        return converter.toDto(employee);
    }

    public ResponseEmployeeDTO updateEmployeeSurnameById(Integer id, String surname) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setSurname(surname);

        employeeRepository.save(employee);

        return converter.toDto(employee);
    }

    public ResponseEmployeeDTO updateEmployeeEmailById(Integer id, String email) {
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

        return converter.toDto(employee);
    }

    private boolean isEmailAlreadyExist(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }


}
