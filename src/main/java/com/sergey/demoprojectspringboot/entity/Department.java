package com.sergey.demoprojectspringboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Department {


    private String name;
    private Integer id;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployees(Employee employee) {
        employees.add(employee);
    }

}
