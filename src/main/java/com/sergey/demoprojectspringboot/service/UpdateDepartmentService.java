package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UpdateDepartmentService {

    private DepartmentRepositoryInterface departmentRepository;

    public GlobalResponce<ResponceDepartmentDTO> updateDepartmentNameById(String name,Integer id){
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND,null,"Department not found");
        }
        if(!isNameAlreadyExist(name)) {
            return new GlobalResponce<>(HttpStatus.CONFLICT,null,"Department name already exist");
        }
        if (!LATIN_PATTERN.matcher(name).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT,null,"Name is invalid");
        }

        Department department = departmentOptional.get();
        department.setName(name);

        departmentRepository.saveForUpdate(department);

        return new GlobalResponce<>(HttpStatus.OK,ResponceDepartmentDTO.toDto(department),"Department updated successfully");
    }

    private boolean isNameAlreadyExist(String name) {
        return departmentRepository.findByName(name).isEmpty();
    }

    private static final Pattern LATIN_PATTERN = Pattern.compile("^[A-Za-z._!-]+$");
}
