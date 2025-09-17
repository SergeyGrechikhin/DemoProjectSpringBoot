package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor

public class UpdateDepartmentService {

    private DepartmentRepositoryDataBase departmentRepository;
    private ValidationService validationService;

    public ResponceDepartmentDTO updateDepartmentNameById(String name,Integer id){
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()) {
            throw new NotFoundException("Department not found");
        }
        if(!isNameAlreadyExist(name)) {
            throw new AlreadyExistException("Department name already exist");
        }

        Department department = departmentOptional.get();
        department.setName(name);

        departmentRepository.save(department);

        return ResponceDepartmentDTO.toDto(department);
    }

    private boolean isNameAlreadyExist(String name) {
        return departmentRepository.findByName(name).isEmpty();
    }


}
