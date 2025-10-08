package com.sergey.demoprojectspringboot.dto.responceDto;

import com.sergey.demoprojectspringboot.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseEmployeeDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Employee.Role role;
    private Employee.Status status;


}


