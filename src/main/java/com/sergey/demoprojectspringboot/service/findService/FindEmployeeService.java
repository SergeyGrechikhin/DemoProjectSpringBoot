package com.sergey.demoprojectspringboot.service.findService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FindEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;
    private Converter converter;

    public List<ResponceEmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employees.stream().map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getRole())).toList();
        if(responceEmployeeDTOList.isEmpty()){
            throw  new NotFoundException(" Employees not found ");
        }
        return responceEmployeeDTOList;
    }

//    public ResponceEmployeeDTO findById(Integer id) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        if(employeeOptional.isEmpty()){
//            throw  new NotFoundException(" Employee with this" + id + " id not found ");
//        }
//        return ResponceEmployeeDTO.toDTO(employeeOptional.get());
//    }

    public ResponceEmployeeDTO getUserById(Integer id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id = " + id + " not found"));

        return converter.toDto(employee);
    }

    public List<ResponceEmployeeDTO> findByName(String name) {
        List<Employee> employeesOptionalList = employeeRepository.findByNameContainingIgnoreCase(name);
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getName().equalsIgnoreCase(name)).map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(),employee.getEmail(),employee.getRole())).toList();
        if(employeesOptionalList.isEmpty()){
            throw  new NotFoundException(" Employee with this  " + name + " name not found ");
        }
        return responceEmployeeDTOList;
    }

    public List<ResponceEmployeeDTO> findBySurname(String surname) {
        List<Employee> employeesOptionalList = employeeRepository.findBySurname(surname);
        List<ResponceEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getSurname().equalsIgnoreCase(surname)).map(employee -> new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(),employee.getEmail(),employee.getRole())).toList();
        if(employeesOptionalList.isEmpty()){
            throw  new NotFoundException(" Employee with this  " + surname + " surname not found ");
        }
        return responceEmployeeDTOList;
    }

//    public ResponceEmployeeDTO findByEmail(String email) {
//        Optional<Employee> employeesOptional = employeeRepository.findByEmail(email);
//        if(employeesOptional.isEmpty()){
//            throw  new NotFoundException(" Employee with this " + email + " email not found ");
//        }
//        return ResponceEmployeeDTO.toDTO(employeesOptional.get());
//    }
//
//    public ResponceEmployeeDTO getUserById(Integer id){
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("User with id = " + id + " not found"));
//
//        return converter.toDto(employee);
//    }



    public Optional<Employee> findByIdForService(Integer id) {
        return employeeRepository.findById(id);
    }


}
