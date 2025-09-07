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

@Service
@AllArgsConstructor
public class AddDepartmentService {
    private DepartmentRepositoryInterface departmentRepository;

    public GlobalResponce<ResponceDepartmentDTO> create(RequestCreateDepartmentDTO request) {
        Department department = departmentRepository.add(new Department(request.getName()));
        return new GlobalResponce<>(HttpStatus.CREATED, ResponceDepartmentDTO.toDto(department));
    }





}
