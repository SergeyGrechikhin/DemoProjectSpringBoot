package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class FindEmployeeService {
    private EmployeeRepositoryInterface employeeRepository;

    public String findAll(){
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            return "Employee not found";
        }
        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList();
        for(Employee employee : employees){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname().toString()));
        }
        return responceEmployeeDTOList.toString();
    }

    public String findbyId(Integer id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            return "Employee not found";
        }
        Employee employee1 = employee.get();
        return new ResponceEmployeeDTO(employee1.getId(),employee1.getName(),employee1.getSurname()).toString();
    }

    public String findbyName(String name){
        List<Employee> employees = employeeRepository.findByName(name);
        if(employees.isEmpty()){
            return "Employee not found";
        }
        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList();
        for(Employee employee : employees){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
        }
        return responceEmployeeDTOList.toString();
    }

    public String findbySurname(String surname){
        List<Employee> employees = employeeRepository.findBySurname(surname);
        if(employees.isEmpty()){
            return "Employee not found";
        }
        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList();
        for(Employee employee : employees){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
        }
        return responceEmployeeDTOList.toString();
    }

    public Optional<Employee> findEntityId(Integer id){
        return employeeRepository.findById(id);
    }
}
