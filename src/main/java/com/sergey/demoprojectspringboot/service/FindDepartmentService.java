package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class FindDepartmentService {

    private DepartmentCoreService departmentCoreService;

    public String findAllDepartments() {
        List<Department> list = departmentCoreService.getAllDepartments();
        if (list.isEmpty()) {
            return "Department not found";
        }
        List<ResponceDepartmentDTO> responceDepartmentDTOList = new ArrayList<>();
        for (Department department : list) {
            List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList<>();
            for (Employee employee : department.getEmployees()) {
                responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname()));
            }
            responceDepartmentDTOList.add(new ResponceDepartmentDTO(department.getName(), responceEmployeeDTOList));
        }
        return responceDepartmentDTOList.toString();
    }
}
