package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.GlobalResponce;
import com.sergey.demoprojectspringboot.repository.EmployeeRepository;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AddEmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;
    private ValidationService validationService;
    private FindDepartmentService findDepartmentService;
    private AddEmployeeToDepartmentService addEmployeeToDepartmentService;


    @Transactional
    public GlobalResponce<ResponceEmployeeDTO> createEmployee(RequestAddEmployeeDTO request) {
        Optional<Employee> emailOptional = employeeRepository.findByEmail(request.getEmail());
        Optional<Department> departmentOptional = findDepartmentService.findDepartmentByName(request.getDepartmentName());
        if (departmentOptional.isEmpty()) {
            return new GlobalResponce<>(HttpStatus.NOT_FOUND, null, "Department Not Found");
        }
        if (emailOptional.isPresent()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Employee with this email already exists");
        }
        if (request.getName() == null || request.getName().isEmpty() || request.getName().equals("")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Employee name is empty");
        }
        if (request.getSurname() == null || request.getSurname().isEmpty() || request.getName().equals("")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Surname is empty");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty() || request.getName().equals("")) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email is empty");
        }
        if (!validationService.LATIN_PATTERN.matcher(request.getSurname()).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Surname is invalid");
        }
        if (!validationService.LATIN_PATTERN.matcher(request.getName()).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Name is invalid");
        }

        if (!validationService.EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            return new GlobalResponce<>(HttpStatus.CONFLICT, null, " Email is invalid");
        }


        Department department = departmentOptional.get();

        Employee employee = new Employee(request.getName(), request.getSurname(), request.getEmail());

        addEmployeeToDepartmentService.addEmployeeToDepartment(department, employee);

        employee = employeeRepository.save(employee);

       // department.getEmployees().add(employee); Один из вариантов добавление

        department.addEmployee(employee); //Метод с помощью хелпера


        return new GlobalResponce<>(HttpStatus.CREATED, ResponceEmployeeDTO.toDTO(employee), "Employee created successfully");

    }


}







