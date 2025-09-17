package com.sergey.demoprojectspringboot.service;


import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;

import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
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

    public ResponceEmployeeDTO addEmployeeToDepartment(Department department, Employee employee) {
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByIdForService(department.getId());
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException("Department Not Found");
        }
        Department departmentForInvite = departmentOptional.get();

        employee.setDepartment(departmentForInvite);

        departmentForInvite.getEmployees().add(employee);

        Employee employeeSave = employeeRepositoryDataBase.save(employee);

        return ResponceEmployeeDTO.toDTO(employeeSave);

    }

    public ResponceEmployeeDTO addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        Optional<Employee> employeeForUpdate = employeeFindService.findByIdForService(employeeId);
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByIdForService(departmentId);

        if (employeeForUpdate.isEmpty()) {
            throw  new NotFoundException(" Employee " + " with " + employeeId + " Not Found ");
        }
        if (departmentOptional.isEmpty()) {
            throw  new NotFoundException(" Department " + " with " + departmentId + " Not Found ");
        }

        Employee employee = employeeForUpdate.get();

        employee.setDepartment(departmentOptional.get());

        employeeRepositoryDataBase.save(employee);

        return ResponceEmployeeDTO.toDTO(employee);
    }


}



