package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;


public class DepartmentRepository implements DepartmentRepositoryInterface {

    private Map<Integer, Department> database;
    private Integer departmentId;

    public DepartmentRepository() {
        this.database = new HashMap<>();
        this.departmentId = 0;
    }

    @Override
    public Department add(Department department) {
        department.setId(++departmentId);
        database.put(departmentId, department);
        return department;
    }

    @Override
    public List<Department> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Department> findById(Integer id) {
        Optional<Department> departmentOptional = database.values().stream().filter(d -> d.getId().equals(id)).findFirst();
        return departmentOptional;
    }

    @Override
    public Optional<Department> findByName(String name) {
        Optional<Department> departmentOptional = database.values().stream().filter(department -> department.getName().equalsIgnoreCase(name)).findFirst();

        return departmentOptional;
    }

    @Override
    public Optional<Department> deleteById(Integer id) {
        Department departmentForDelete = database.remove(id);
        return Optional.ofNullable(departmentForDelete);
    }

    @Override
    public Optional<Department> addEmployee(String departmentName, Employee employee) {
        Department department = database.values().stream().filter(d -> d.getName().equalsIgnoreCase(departmentName)).findFirst().orElse(null);
        if (department == null) {
            return Optional.empty();
        }
        department.getEmployees().add(employee);
        return Optional.of(department);
    }

    @Override
    public Department saveForUpdate(Department department) {
        database.put(department.getId(), department);
        return department;
    }
}
