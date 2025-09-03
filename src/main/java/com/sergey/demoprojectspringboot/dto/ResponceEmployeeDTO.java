package com.sergey.demoprojectspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class ResponceEmployeeDTO {
    private Integer id;
    private String name;
    private String surname;
}
