package com.sergey.demoprojectspringboot.service.addService;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
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

    /**
     * Create Department
     * @param request
     * @return ResponceDepartmentDTO with created department
     */
    public ResponseDepartmentDTO create(RequestCreateDepartmentDTO request) {
        Optional<Department> isNameTry = departmentRepository.findByName(request.getName());
        if (isNameTry.isPresent()) {
            throw new AlreadyExistException("Department with name " + request.getName() + " already exists");
        }

        Department department = departmentRepository.save(new Department(request.getName()));

        return ResponseDepartmentDTO.toDto(department);
    }










}
