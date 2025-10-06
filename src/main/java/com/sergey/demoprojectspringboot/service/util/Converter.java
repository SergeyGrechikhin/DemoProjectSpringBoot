package com.sergey.demoprojectspringboot.service.util;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Converter {
    public Employee fromDto(RequestAddEmployeeDTO request){

        return Employee.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public ResponceEmployeeDTO toDto(Employee employee){
        return ResponceEmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .surname(employee.getSurname())
                .role(employee.getRole())
                .build();
    }

    public List<ResponceEmployeeDTO> fromEmployees(List<Employee> employees){
        return employees.stream()
                .map(employee -> toDto(employee))
                .toList();
    }
}
