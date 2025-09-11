package com.sergey.demoprojectspringboot.service;


import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;

import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

@Service
@AllArgsConstructor

public class AddEmployeeToDepartmentService {
    private FindEmployeeService employeeFindService;
    private FindDepartmentService departmentFindService;
    private EmployeeRepositoryDataBase employeeRepositoryDataBase;

 /*   public GlobalResponce<ResponceEmployeeDTO> addEmployeeToDepartment1(Department department, Employee employee) {
        Optional<Employee> employeeOptional = employeeFindService.findByIdForService(employee.getId());
        if (employeeOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null, "Employee not found");
        }
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByIdForService(department.getId());
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null, "Department not found");
        }
        Employee employeeForInvite = employeeOptional.get();
        employeeForInvite.setDepartment(departmentOptional.get());

        employeeRepositoryDataBase.save(employeeForInvite);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employeeForInvite), "Employee updated successfully");
    }*/

    public GlobalResponce<ResponceEmployeeDTO> addEmployeeToDepartment(Department department, Employee employee) {
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByIdForService(department.getId());
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null, "Department not found");
        }
        Department departmentForInvite = departmentOptional.get();
        employee.setDepartment(departmentForInvite);
        departmentForInvite.getEmployees().add(employee);
        Employee employeeSave = employeeRepositoryDataBase.save(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employeeSave), "Employee saved successfully");

    }

    public GlobalResponce<ResponceEmployeeDTO> addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        Optional<Employee> employeeForUpdate = employeeFindService.findByIdForService(employeeId);
        Optional<Department> departmentOptional= departmentFindService.findDepartmentByIdForService(departmentId);

        if (employeeForUpdate.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Employee not found");
        }
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Department not found");
        }

        Employee employee = employeeForUpdate.get();
        employee.setDepartment(departmentOptional.get());

        employeeRepositoryDataBase.save(employee);

        return new GlobalResponce<>(HttpStatus.OK, ResponceEmployeeDTO.toDTO(employee), "Employee updated successfully");
    }


}



