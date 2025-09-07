package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindDepartmentService {

    private DepartmentRepositoryInterface departmentRepository;

    public String findAll(){
        List<Department> list = departmentRepository.findAll();
        if(list.isEmpty()){
            return "Department not found";
        }
        List<ResponceDepartmentDTO> responceDepartmentDTOList = new ArrayList<>();
        for(Department department : list){
            List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList<>();
            for(Employee employee : department.getEmployees()){
                responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
            }
            responceDepartmentDTOList.add(new ResponceDepartmentDTO(department.getName(),responceEmployeeDTOList));
        }
        return responceDepartmentDTOList.toString();
    }

    public String findById(Integer id){
        Optional<Department> departmentList = departmentRepository.findById(id);
        if(departmentList.isEmpty()){
            return "Department not found";
        }
        Department department1 = departmentList.get();
        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList<>();
        for(Employee employee : department1.getEmployees()){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
        }
        return new ResponceDepartmentDTO(department1.getName(),responceEmployeeDTOList).toString();
    }

    public String findByName(String name){
        Optional<Department> departmentList = departmentRepository.findByName(name);
        if(departmentList.isEmpty()){
            return "Department not found";
        }
        Department department1 = departmentList.get();
        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList<>();
        for(Employee employee : department1.getEmployees()){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
        }
        return new ResponceDepartmentDTO(department1.getName(),responceEmployeeDTOList).toString();
    }

    public String employeeFromDepartment(String name){
        Optional<Department> departmentList = departmentRepository.findByName(name);
        if(departmentList.isEmpty()){
            return "Department not found";
        }
        List<ResponceEmployeeDTO> responceEmployeeDTOList = new ArrayList<>();
        for(Employee employee : departmentList.get().getEmployees()){
            responceEmployeeDTOList.add(new ResponceEmployeeDTO(employee.getId(),employee.getName(),employee.getSurname()));
        }
        if(responceEmployeeDTOList.isEmpty()){
            return "Department not found";
        }
        return responceEmployeeDTOList.toString();
    }

    public Optional<Department> findEntityDepartmentByName(String name){
        return departmentRepository.findByName(name);
    }
}
