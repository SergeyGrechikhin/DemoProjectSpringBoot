package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FindDepartmentService {

    private DepartmentRepositoryDataBase departmentRepository;


    public List<ResponceDepartmentDTO> findAll() {
        List<Department> list = departmentRepository.findAll();
        List<ResponceDepartmentDTO> listDTO = list.stream().map(department -> new ResponceDepartmentDTO(department.getId(), department.getName())).toList();
        if (list.isEmpty()) {
            throw new NotFoundException(" Department Not Found ");
        }
        return listDTO;
    }

    public ResponceDepartmentDTO findById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department Not Found ");
        }
        return ResponceDepartmentDTO.toDto(departmentOptional.get());
    }

    public ResponceDepartmentDTO findByName(String name) {
        Optional<Department> departmentOptional = departmentRepository.findByName(name);
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department Not Found ");
        }
        return ResponceDepartmentDTO.toDto(departmentOptional.get());
    }


    public List<ResponceEmployeeDTO> getEmployeesFromDepartment(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findByName(departmentName);
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department Not Found ");
        }
        List<ResponceEmployeeDTO> responceEmployeeDTOList = departmentOptional.get().getEmployees().stream().map(employee -> ResponceEmployeeDTO.toDTO(employee)).toList();
        return responceEmployeeDTOList;
    }

    public Optional<Department> findDepartmentByIdForService(Integer id) {
        return departmentRepository.findById(id);
    }

    public Optional<Department> findDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }


}
