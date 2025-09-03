package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentCoreService {
    private DepartmentRepositoryInterface departmentRepository;

    public Department create(RequestCreateDepartmentDTO request) {
        return departmentRepository.add(new Department(request.getName()));
    }

    public Optional<Department> addEmployeeByDepartmentName(String departmentName, Employee employee) {
        return departmentRepository.addEmployee(departmentName, employee);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Optional<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

}
