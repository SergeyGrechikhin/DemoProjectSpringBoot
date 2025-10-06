package com.sergey.demoprojectspringboot.service.addService;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.util.Converter;
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
    private Converter converter;
    private CodeConfirmationService codeConfirmationService;


    @Transactional
    public ResponceEmployeeDTO employeeRegistration(RequestAddEmployeeDTO request) {
        Optional<Employee> emailOptional = employeeRepository.findByEmail(request.getEmail());
        Optional<Department> departmentOptional = findDepartmentService.findDepartmentByName(request.getDepartmentName());


        if (emailOptional.isPresent()) {
            throw new AlreadyExistException(" Email Already Exist ");
        }

        Department department = departmentOptional.get();

        Employee newEmployee = converter.fromDto(request);

        newEmployee.setRole(Employee.Role.USER);

        addEmployeeToDepartmentService.addEmployeeToDepartment(department, newEmployee);

        newEmployee = employeeRepository.save(newEmployee);

        codeConfirmationService.confirmationCodeManager(newEmployee);

        return converter.toDto(newEmployee);


    }


}







