package com.sergey.demoprojectspringboot.test;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.AddEmployeeService;
import com.sergey.demoprojectspringboot.service.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.FindEmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddEmployeeTest {

    @Mock
    private EmployeeRepositoryDataBase employeeRepositoryDataBase;

    @Mock
    private DepartmentRepositoryDataBase departmentRepositoryDataBase;

    @Mock
    private FindDepartmentService findDepartmentService;

    @Mock
    private FindEmployeeService findEmployeeService;

    @Mock
    private AddEmployeeToDepartmentService addEmployeeToDepartmentService;

    @InjectMocks
    private AddEmployeeService addEmployeeService;

    @Test
    void createEmployeeSuccessTest() {

        Department department = new Department("IT");

        RequestAddEmployeeDTO req = new RequestAddEmployeeDTO("Ivan", "Ivanov", "google@gmail.com", "IT");


        Employee saved = new Employee(req.getName(), req.getSurname(), req.getEmail());
        saved.setId(1);

        when(employeeRepositoryDataBase.findByEmail(req.getEmail()))
                .thenReturn(Optional.empty());

        when(findDepartmentService.findDepartmentByName(req.getDepartmentName()))
                .thenReturn(Optional.of(department));

        when(employeeRepositoryDataBase.save(any(Employee.class)))
                .thenReturn(saved);


        ResponceEmployeeDTO result = addEmployeeService.createEmployee(req);

        assertEquals("Ivan", result.getName());
        assertEquals("Ivanov", result.getSurname());
        assertEquals("google@gmail.com", result.getEmail());
        assertEquals(1, result.getId());
        assertEquals(req.getName(), result.getName());
    }


}
