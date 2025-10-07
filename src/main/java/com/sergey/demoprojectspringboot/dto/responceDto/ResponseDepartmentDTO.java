package com.sergey.demoprojectspringboot.dto.responceDto;

import com.sergey.demoprojectspringboot.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDepartmentDTO {
    private Integer id;
    private String name;


    public static ResponseDepartmentDTO toDto(Department department){
        return new ResponseDepartmentDTO(department.getId(),department.getName());
    }
}
