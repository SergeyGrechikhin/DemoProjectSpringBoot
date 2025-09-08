package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryInterface {


    Employee add(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    List<Employee> findByName(String name);

    List<Employee> findBySurname(String name);

    Optional<Employee> deleteById(Integer id);

    Optional<Employee> findByEmail(String email);

    Employee saveForUpdate(Employee employee);


}
