package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddDepartmentService {
    private DepartmentRepositoryInterface departmentRepository;

    public GlobalResponce<ResponceDepartmentDTO> create(RequestCreateDepartmentDTO request) {
        Optional<Department> isNameTry = departmentRepository.findByName(request.getName());
        if (isNameTry.isPresent()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT,null);
        }
        Department department = departmentRepository.add(new Department(request.getName()));
        return new GlobalResponce<>(HttpStatus.CREATED, ResponceDepartmentDTO.toDto(department));
    }





}
