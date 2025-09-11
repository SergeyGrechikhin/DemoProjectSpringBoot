package com.sergey.demoprojectspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddEmployeeDTO {
    private String name;
    private String surname;
    private String email;
    private String departmentName;
}
