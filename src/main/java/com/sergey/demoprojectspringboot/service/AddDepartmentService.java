package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor

public class AddDepartmentService {
    private DepartmentRepositoryDataBase departmentRepository;

    public ResponceDepartmentDTO create(RequestCreateDepartmentDTO request) {
        Optional<Department> isNameTry = departmentRepository.findByName(request.getName());
        if (isNameTry.isPresent()) {
            throw new AlreadyExistException("Department with name " + request.getName() + " already exists");
        }

        Department department = departmentRepository.save(new Department(request.getName()));

        return ResponceDepartmentDTO.toDto(department);
    }










}
