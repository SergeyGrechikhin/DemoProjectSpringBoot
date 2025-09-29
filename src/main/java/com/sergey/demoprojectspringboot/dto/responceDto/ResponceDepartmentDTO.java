package com.sergey.demoprojectspringboot.dto.responceDto;

import com.sergey.demoprojectspringboot.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
