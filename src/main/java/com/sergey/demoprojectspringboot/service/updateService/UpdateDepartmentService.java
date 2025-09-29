package com.sergey.demoprojectspringboot.service.updateService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.entity.Department;

import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@AllArgsConstructor

public class UpdateDepartmentService {

    private DepartmentRepositoryDataBase departmentRepository;


    public ResponceDepartmentDTO updateDepartmentNameById(String name,Integer id){
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department with this " + id + "id" + " not found ");
        }
        if(!isNameAlreadyExist(name)) {
            throw new AlreadyExistException("Department with this name " + name + " already exist");
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
