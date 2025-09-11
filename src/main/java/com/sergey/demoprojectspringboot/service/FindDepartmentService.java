package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindDepartmentService {

    private DepartmentRepositoryInterface departmentRepository;
    private FindEmployeeService findEmployeeService;

    public GlobalResponce<List<ResponceDepartmentDTO>> findAll() {
        List<Department> list = departmentRepository.findAll();
        List<ResponceDepartmentDTO> listDTO = list.stream().map(department -> new ResponceDepartmentDTO(department.getId(), department.getName())).toList();
        if (list.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NO_CONTENT, listDTO, "Departments not found");
        }
        return new GlobalResponce<>(HttpStatus.OK, listDTO, "Departments found successfully");
    }

    public GlobalResponce<ResponceDepartmentDTO> findById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null, "Department not found");
        }
        return new GlobalResponce<>(HttpStatus.OK, ResponceDepartmentDTO.toDto(departmentOptional.get()), "Department found successfully");
    }

    public GlobalResponce<ResponceDepartmentDTO> findByName(String name) {
        Optional<Department> departmentOptional = departmentRepository.findByName(name);
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null, "Department not found");
        }
        return new GlobalResponce<>(HttpStatus.OK, ResponceDepartmentDTO.toDto(departmentOptional.get()), "Department found successfully");
    }


    public GlobalResponce<List<ResponceEmployeeDTO>> getEmployeesFromDepartment(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findByName(departmentName);
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.BAD_REQUEST, null, "Department not found");
        }
        List<ResponceEmployeeDTO> responceEmployeeDTOList = departmentOptional.get().getEmployees().stream().map(employee -> ResponceEmployeeDTO.toDTO(employee)).toList();
        return new GlobalResponce<>(HttpStatus.OK, responceEmployeeDTOList, "Employees found successfully");
    }

    public Optional<Department> findDepartmentByNameForService(String name) {
        return departmentRepository.findByName(name);
    }


}
