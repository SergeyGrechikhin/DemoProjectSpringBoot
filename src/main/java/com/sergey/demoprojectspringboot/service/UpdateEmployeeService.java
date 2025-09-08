package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UpdateEmployeeService {
    private EmployeeRepositoryInterface employeeRepository;

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeNameById(Integer id,String name) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if(employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND,null,"Employee not found");
        }
        Employee employee = employeeForUpdate.get();
        employee.setName(name);

        employeeRepository.saveForUpdate(employee);

        return new GlobalResponce<>(HttpStatus.OK,ResponceEmployeeDTO.toDTO(employee),"Employee updated successfully");
    }

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeSurnameById(Integer id,String surname) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if(employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND,null,"Employee not found");
        }
        Employee employee = employeeForUpdate.get();
        employee.setSurname(surname);

        employeeRepository.saveForUpdate(employee);

        return new GlobalResponce<>(HttpStatus.OK,ResponceEmployeeDTO.toDTO(employee),"Employee updated successfully");
    }

    public GlobalResponce<ResponceEmployeeDTO> updateEmployeeEmailById(Integer id,String email) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if(employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND,null,"Employee not found");
        }
        Employee employee = employeeForUpdate.get();
        employee.setEmail(email);

        employeeRepository.saveForUpdate(employee);

        return new GlobalResponce<>(HttpStatus.OK,ResponceEmployeeDTO.toDTO(employee),"Employee updated successfully");
    }
}
