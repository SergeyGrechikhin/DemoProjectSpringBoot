package com.sergey.demoprojectspringboot.dto;

import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponceDepartmentDTO {
    private Integer id;
    private String name;


    public static ResponceDepartmentDTO toDto(Department department){
        return new ResponceDepartmentDTO(department.getId(),department.getName());
    }
}
