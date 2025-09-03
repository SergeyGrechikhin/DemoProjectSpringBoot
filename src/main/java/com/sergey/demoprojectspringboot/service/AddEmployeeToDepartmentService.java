package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AddEmployeeToDepartmentService {
    private EmployeeCoreService employeeCoreService;
    private DepartmentCoreService departmentCoreService;

    public String add(String departmentName, RequestAddEmployeeDTO request){
        Employee savedEmployee = employeeCoreService.createEmployee(request);
        Optional<Department> updated = departmentCoreService.addEmployeeByDepartmentName(departmentName,savedEmployee);
        if (updated.isEmpty()){
            return "Department not found";
        }
        Department updatedDepartment = updated.get();
        List<ResponceEmployeeDTO> employees = new ArrayList<>();
        for (Employee e : updatedDepartment.getEmployees()){
            employees.add(new ResponceEmployeeDTO(e.getId(),e.getName(),e.getSurname()));
        }
        return new ResponceDepartmentDTO(updatedDepartment.getName(),employees).toString();
    }
}
