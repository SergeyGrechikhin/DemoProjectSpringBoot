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
    private FindEmployeeService employeeFindService;
    private FindDepartmentService departmentFindService;

    public String addEmployeeToDepartment(String departmentName, Integer employeeId) {
        Optional<Employee> employeeList = employeeFindService.findEntityId(employeeId);
        if(employeeList.isEmpty()){
            return "Employee Not Found";
        }
        Optional<Department> departmentList = departmentFindService.findEntityDepartmentByName(departmentName);
        if(departmentList.isEmpty()){
            return "Department Not Found";
        }
        Department department = departmentList.get();
        department.getEmployees().add(employeeList.get());

        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList<>();
        for(Employee employee : department.getEmployees()){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
        }
        return new ResponceDepartmentDTO(department.getName(),responceEmployeeDTOList).toString();
    }
}
