package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindEmployeeService {
    private EmployeeRepositoryInterface employeeRepository;

    public GlobalResponce<List<ResponceEmployeeDTO>> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employees.stream().map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname())).toList();
        if(responceEmployeeDTOList.isEmpty()){
            return new GlobalResponce<>(HttpStatus.NO_CONTENT,responceEmployeeDTOList);
        }
        return new GlobalResponce<>(HttpStatus.OK,responceEmployeeDTOList);
    }

    public GlobalResponce<ResponceEmployeeDTO> findById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST,null);
        }
        return new GlobalResponce<>(HttpStatus.OK,ResponceEmployeeDTO.toDTO(employeeOptional.get()));
    }

    public GlobalResponce<List<ResponceEmployeeDTO>> findByName(String name) {
        List<Employee> employeesOptionalList = employeeRepository.findByName(name);
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getName().equalsIgnoreCase(name)).map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname())).toList();
        if(employeesOptionalList.isEmpty()){
            return new GlobalResponce<>(HttpStatus.NO_CONTENT,null);
        }
        return new GlobalResponce<>(HttpStatus.OK,responceEmployeeDTOList);
    }

    public GlobalResponce<List<ResponceEmployeeDTO>> findBySurname(String surname) {
        List<Employee> employeesOptionalList = employeeRepository.findBySurname(surname);
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getSurname().equalsIgnoreCase(surname)).map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname())).toList();
        if(employeesOptionalList.isEmpty()){
            return new GlobalResponce<>(HttpStatus.NO_CONTENT,null);
        }
        return new GlobalResponce<>(HttpStatus.OK,responceEmployeeDTOList);
    }



    public Optional<Employee> findByIdForService(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllForService() {
        return employeeRepository.findAll();
    }
}
