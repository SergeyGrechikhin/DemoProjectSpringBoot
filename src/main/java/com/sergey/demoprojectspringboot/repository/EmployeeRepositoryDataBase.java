package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface EmployeeRepositoryDataBase extends JpaRepository<Employee, Integer> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findBySurname(String surname);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByStatusAndDeactivateAtBefore(Employee.Status status, LocalDateTime deactivateAt);


    @Modifying
    @Transactional
    long deleteByStatusAndDeactivateAtBefore(Employee.Status status, LocalDateTime deactivatedDate);




}
