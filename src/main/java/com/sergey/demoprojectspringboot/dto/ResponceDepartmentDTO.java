package com.sergey.demoprojectspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponceDepartmentDTO {
    private String name;
    private List<ResponceEmployeeDTO> employees;
}
