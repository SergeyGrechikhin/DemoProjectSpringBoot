package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeCoreService {
    private EmployeeRepository employeeRepository;
    public Employee createEmployee(RequestAddEmployeeDTO request){
        Employee employee = new Employee(request.getName(),request.getSurname());
        return employeeRepository.add(employee);
    }
}
