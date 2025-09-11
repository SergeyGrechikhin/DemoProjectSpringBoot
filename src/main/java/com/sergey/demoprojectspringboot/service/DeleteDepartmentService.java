package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
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

    public GlobalResponce<ResponceDepartmentDTO> deleteDepartment(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null,"Department not found");
        }

        Department department = departmentOptional.get();
        departmentRepository.delete(department);

        return new GlobalResponce<>(HttpStatus.OK, ResponceDepartmentDTO.toDto(department),"Department deleted successfully");

    }
}
