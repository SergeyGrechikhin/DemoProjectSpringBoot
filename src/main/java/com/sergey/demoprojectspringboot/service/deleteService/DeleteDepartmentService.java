package com.sergey.demoprojectspringboot.service.deleteService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@AllArgsConstructor

public class DeleteDepartmentService {
    private DepartmentRepositoryDataBase departmentRepository;

    public ResponseDepartmentDTO deleteDepartment(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department " + " with " + id + " id " + " not found ");
        }
        if (departmentOptional.get().getId() == 1) {
            throw new BadRequestException("Department " + " with " + id + " id " + " cannot be deleted");
        }

        Department department = departmentOptional.get();
        departmentRepository.delete(department);

        return ResponseDepartmentDTO.toDto(department);

    }
}
