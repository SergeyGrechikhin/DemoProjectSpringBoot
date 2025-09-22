package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AddEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;

    private FindDepartmentService findDepartmentService;
    private AddEmployeeToDepartmentService addEmployeeToDepartmentService;


    @Transactional
    public ResponceEmployeeDTO createEmployee(RequestAddEmployeeDTO request) {
        Optional<Employee> emailOptional = employeeRepository.findByEmail(request.getEmail());
        Optional<Department> departmentOptional = findDepartmentService.findDepartmentByName(request.getDepartmentName());


        if (emailOptional.isPresent()) {
            throw  new AlreadyExistException(" Email Already Exist ");
        }

        Department department = departmentOptional.get();

        Employee employee = new Employee(request.getName(), request.getSurname(), request.getEmail());

        addEmployeeToDepartmentService.addEmployeeToDepartment(department, employee);

        employee = employeeRepository.save(employee);

       // department.getEmployees().add(employee); Один из вариантов добавление

        department.addEmployee(employee); //Метод с помощью хелпера


        return ResponceEmployeeDTO.toDTO(employee);

    }


}







