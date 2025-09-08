package com.sergey.demoprojectspringboot.service;


import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddEmployeeToDepartmentService {
    private FindEmployeeService employeeFindService;
    private FindDepartmentService departmentFindService;
    private DepartmentRepositoryInterface departmentRepository;

    public GlobalResponce<ResponceDepartmentDTO> addEmployeeToDepartment(String departmentName, Integer employeeId) {
        Optional<Employee> employeeOptional = employeeFindService.findByIdForService(employeeId);
        if (employeeOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null,"Employee not found");
        }
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByNameForService(departmentName);
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null,"Department not found");
        }
        Optional<Department> updateDepartmentOptional = departmentRepository.addEmployee(departmentName, employeeOptional.get());
        if (updateDepartmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null,"Department not found");
        }
        return new GlobalResponce<>(HttpStatus.OK, ResponceDepartmentDTO.toDto(updateDepartmentOptional.get()),"Department updated successfully");
    }
}



