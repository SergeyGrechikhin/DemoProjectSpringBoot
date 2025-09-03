package com.sergey.demoprojectspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private String surname;


    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
