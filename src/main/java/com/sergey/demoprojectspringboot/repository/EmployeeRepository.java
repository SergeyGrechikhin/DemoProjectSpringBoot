package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Employee;


import java.util.*;


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
        Optional<Employee> employeeOptional = database.values().stream().filter(d -> d.getId().equals(id)).findFirst();
        return employeeOptional;
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

    @Override
    public Optional<Employee> findByEmail(String email) {
        Optional<Employee> employeeOptional = database.values().stream().filter(e -> e.getEmail().equalsIgnoreCase(email)).findFirst();
        return employeeOptional;
    }

    @Override
    public Employee saveForUpdate(Employee employee) {
        database.put(employee.getId(), employee);
        return employee;
    }
}
