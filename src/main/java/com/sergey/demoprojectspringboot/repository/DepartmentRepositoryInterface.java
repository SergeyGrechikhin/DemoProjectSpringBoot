package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryInterface {
    Department add(Department department);

    List<Department> findAll();

    Optional<Department> findById(Integer id);

    Optional<Department> findByName(String name);

    Optional<Department> deleteById(Integer id);

    Optional<Department> addEmployee(String departmentName, Employee employee);

    Department saveForUpdate(Department department);


}
