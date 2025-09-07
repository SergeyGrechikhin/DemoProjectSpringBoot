package com.sergey.demoprojectspringboot.dto;

import com.sergey.demoprojectspringboot.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceEmployeeDTO {
    private Integer id;
    private String name;
    private String surname;

    public static ResponceEmployeeDTO toDTO(Employee employee){
        return new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname());
    }
}


