package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor

public class DeleteDepartmentService {
    private DepartmentRepositoryDataBase departmentRepository;

    public ResponceDepartmentDTO deleteDepartment(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department " + " with " + id + " Not Found ");
        }

        Department department = departmentOptional.get();
        departmentRepository.delete(department);

        return ResponceDepartmentDTO.toDto(department);

    }
}
