package com.sergey.demoprojectspringboot.test;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeAlreadyExistTest {

    @Mock
     private AddEmployeeToDepartmentService addEmployeeToDepartmentService;

    @InjectMocks
    private AddEmployeeService addEmployeeService;

    @Mock
    private EmployeeRepositoryDataBase employeeRepositoryDataBase;

    @Mock
    private DepartmentRepositoryDataBase departmentRepositoryDataBase;

    @Mock
    private FindDepartmentService findDepartmentService;

    @Mock
    private FindEmployeeService findEmployeeService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployeeAlreadyExistTest() {
        // подготовительные действия
        RequestAddEmployeeDTO request = new RequestAddEmployeeDTO("Ivan","Ivanov","google@gmail.com","IT");

        when(employeeRepositoryDataBase.findByEmail(request.getEmail())).thenReturn(Optional.of(new Employee()));

        assertThrows(AlreadyExistException.class, () -> addEmployeeService.createEmployee(request));

    }

}
