package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface EmployeeRepositoryDataBase extends JpaRepository<Employee, Integer> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findBySurname(String surname);

    Optional<Employee> findByEmail(String email);


}
