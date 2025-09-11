package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class AddDepartmentService {
    private DepartmentRepositoryDataBase departmentRepository;
    private ValidationService validationService;

    public GlobalResponce<ResponceDepartmentDTO> create(RequestCreateDepartmentDTO request) {
        Optional<Department> isNameTry = departmentRepository.findByName(request.getName());
        if (isNameTry.isPresent()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT,null,"Name from Department already exists");
        }
        if(request.getName() == null || request.getName().equals("") || request.getName().isEmpty()){
            return new GlobalResponce<>(HttpStatus.CONFLICT,null," Name cannot be empty");
        }
        if(!validationService.LATIN_PATTERN.matcher(request.getName()).matches()){
            return new GlobalResponce<>(HttpStatus.CONFLICT,null,"Name is not a properly formatted name");

        }

        Department department = departmentRepository.save(new Department(request.getName()));
        return new GlobalResponce<>(HttpStatus.CREATED, ResponceDepartmentDTO.toDto(department),"Department created successfully");
    }







}
