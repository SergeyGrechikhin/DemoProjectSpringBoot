package com.sergey.demoprojectspringboot.service.findService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;

import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.EmployeeConverter;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FindDepartmentService {

    private DepartmentRepositoryDataBase departmentRepository;
    private EmployeeConverter converter;


    public List<ResponseDepartmentDTO> findAll() {
        List<Department> list = departmentRepository.findAll();
        List<ResponseDepartmentDTO> listDTO = list.stream().map(department -> new ResponseDepartmentDTO(department.getId(), department.getName())).toList();
        if (list.isEmpty()) {
            throw new NotFoundException(" Departments not found ");
        }
        return listDTO;
    }

    public ResponseDepartmentDTO findById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department with this " + id + " id " + " not found ");
        }
        return ResponseDepartmentDTO.toDto(departmentOptional.get());
    }

    public ResponseDepartmentDTO findByName(String name) {
        Optional<Department> departmentOptional = departmentRepository.findByName(name);
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department with this name " + name + " not found ");
        }
        return ResponseDepartmentDTO.toDto(departmentOptional.get());
    }


    public List<ResponseEmployeeDTO> getEmployeesFromDepartment(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findByName(departmentName);
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department with this name " + departmentName + " not found ");
        }
        List<ResponseEmployeeDTO> responceEmployeeDTOList = departmentOptional.get().getEmployees().stream().map(employee -> converter.toDto(employee)).toList();
        return responceEmployeeDTOList;
    }

    public Optional<Department> findDepartmentByIdForService(Integer id) {
        return departmentRepository.findById(id);
    }

    public Optional<Department> findDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }


}
