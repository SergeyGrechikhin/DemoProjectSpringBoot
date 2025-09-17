package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FindEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;

    public List<ResponceEmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employees.stream().map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail())).toList();
        if(responceEmployeeDTOList.isEmpty()){
            throw  new NotFoundException(" Employees not found ");
        }
        return responceEmployeeDTOList;
    }

    public ResponceEmployeeDTO findById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            throw  new NotFoundException(" Employee with this" + id + " id not found ");
        }
        return ResponceEmployeeDTO.toDTO(employeeOptional.get());
    }

    public List<ResponceEmployeeDTO> findByName(String name) {
        List<Employee> employeesOptionalList = employeeRepository.findByNameContainingIgnoreCase(name);
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getName().equalsIgnoreCase(name)).map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(),employee.getEmail())).toList();
        if(employeesOptionalList.isEmpty()){
            throw  new NotFoundException(" Employee with this  " + name + " name not found ");
        }
        return responceEmployeeDTOList;
    }

    public List<ResponceEmployeeDTO> findBySurname(String surname) {
        List<Employee> employeesOptionalList = employeeRepository.findBySurname(surname);
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getSurname().equalsIgnoreCase(surname)).map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(),employee.getEmail())).toList();
        if(employeesOptionalList.isEmpty()){
            throw  new NotFoundException(" Employee with this  " + surname + " surname not found ");
        }
        return responceEmployeeDTOList;
    }

    public ResponceEmployeeDTO findByEmail(String email) {
        Optional<Employee> employeesOptionalList = employeeRepository.findByEmail(email);
        if(employeesOptionalList.isEmpty()){
            throw  new NotFoundException(" Employee with this " + email + " email not found ");
        }
        return ResponceEmployeeDTO.toDTO(employeesOptionalList.get());
    }



    public Optional<Employee> findByIdForService(Integer id) {
        return employeeRepository.findById(id);
    }


}
