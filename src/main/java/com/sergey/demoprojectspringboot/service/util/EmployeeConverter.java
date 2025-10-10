package com.sergey.demoprojectspringboot.service.util;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestEmployeeDto;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeConverter {

    private final PasswordEncoder passwordEncoder;

    public Employee fromDto(RequestEmployeeDto request){

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        return Employee.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();
    }

    public ResponseEmployeeDTO toDto(Employee employee){
        return ResponseEmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .email(employee.getEmail())
                .role(employee.getRole())
                .status(employee.getStatus())
                .build();
    }

    public List<ResponseEmployeeDTO> fromEmployees(List<Employee> employees){
        return employees.stream()
                .map(employee -> toDto(employee))
                .toList();
    }
}
