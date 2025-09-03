package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository implements EmployeeRepositoryInterface{

    private Map<Integer, Employee> database;
    private Integer employeeId;

    public EmployeeRepository() {
        this.database = new HashMap<>();
        this.employeeId = 0;
    }

    @Override
    public Employee add(Employee employee) {
        employee.setId(++employeeId);
        database.put(employeeId, employee);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Employee> findByName(String name) {
        return database.values().stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .toList();
    }

    @Override
    public List<Employee> findBySurname(String surname) {
        return database.values().stream()
                .filter(e -> e.getSurname().equalsIgnoreCase(surname))
                .toList();
    }

    @Override
    public Optional<Employee> deleteById(Integer id) {
        Employee employeeForDelete = database.remove(id);
        return Optional.ofNullable(employeeForDelete);
    }
}
