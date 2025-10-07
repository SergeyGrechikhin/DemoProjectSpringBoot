package com.sergey.demoprojectspringboot.service.addService;


import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import com.sergey.demoprojectspringboot.service.util.EmployeeConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class AddEmployeeToDepartmentService {
    private FindEmployeeService employeeFindService;
    private FindDepartmentService departmentFindService;
    private EmployeeRepositoryDataBase employeeRepositoryDataBase;
    private EmployeeConverter converter;

    public ResponceEmployeeDTO addEmployeeToDepartment(Department department, Employee employee) {
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByIdForService(department.getId());
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException("Department with name " + department.getName() + " not found ");
        }
        Department departmentForInvite = departmentOptional.get();

        employee.setDepartment(departmentForInvite);

        departmentForInvite.getEmployees().add(employee);

        Employee employeeSave = employeeRepositoryDataBase.save(employee);

        return converter.toDto(employeeSave);

    }

    public ResponceEmployeeDTO addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        Optional<Employee> employeeForUpdate = employeeFindService.findByIdForService(employeeId);
        Optional<Department> departmentOptional = departmentFindService.findDepartmentByIdForService(departmentId);

        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee " + " with " + employeeId + " id " + " not found ");
        }
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department " + " with " + departmentId + " id " + " not found ");
        }

        Employee employee = employeeForUpdate.get();

        employee.setDepartment(departmentOptional.get());

        employeeRepositoryDataBase.save(employee);

        return converter.toDto(employee);
    }


}



