package com.sergey.demoprojectspringboot.test;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.AddEmployeeService;
import com.sergey.demoprojectspringboot.service.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.FindEmployeeService;
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

public class EmployeeAddTest {

    @InjectMocks
     private AddEmployeeToDepartmentService addEmployeeToDepartmentService;

    @InjectMocks
    private AddEmployeeService addEmployeeService;

    @Mock
    private EmployeeRepositoryDataBase employeeRepositoryDataBase;

    @Mock
    private DepartmentRepositoryDataBase departmentRepositoryDataBase;

    @InjectMocks
    private FindDepartmentService findDepartmentService;

    @InjectMocks
    private FindEmployeeService findEmployeeService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployeeSuccessTest() {
        RequestCreateDepartmentDTO requestCreateDepartmentDTO = new RequestCreateDepartmentDTO("IT");
        Department department = new Department("IT");
        RequestAddEmployeeDTO requestEmployeeDTO = new RequestAddEmployeeDTO("Ivan","Ivanov","google@gmail.com","IT");
        Employee employeeRequest = new Employee("Ivan", "Ivanov","google@gmail.com");
        Employee employeeResponse = new Employee(1,"Ivan", "Ivanov","google@gmail.com");
        ResponceEmployeeDTO responceEmployeeDTO = new ResponceEmployeeDTO(1,"Ivan", "Ivanov","google@gmail.com");

     //   when(findEmployeeService.findByEmail(requestEmployeeDTO.getEmail())).thenThrow(NotFoundException.class);
        when(findDepartmentService.findDepartmentByName(requestCreateDepartmentDTO.getName())).thenReturn(Optional.of(department));
        when(employeeRepositoryDataBase.save(employeeRequest)).thenReturn(employeeResponse);
        when(addEmployeeService.createEmployee(any(RequestAddEmployeeDTO.class))).thenReturn(responceEmployeeDTO);


        ResponceEmployeeDTO testResult = addEmployeeService.createEmployee(requestEmployeeDTO);



        assertThrows(NotFoundException.class, () -> findEmployeeService.findByEmail(any(String.class)));
        assertEquals(responceEmployeeDTO, addEmployeeService.createEmployee(requestEmployeeDTO));
        assertEquals("Ivan", testResult.getName());
        assertEquals("google@gmail.com", testResult.getEmail());
        assertEquals(1, testResult.getId());
        assertEquals("Ivanov", testResult.getSurname());
    }

    @Test
    void createEmployeeAlreadyExistTest() {
        // подготовительные действия
        RequestAddEmployeeDTO request = new RequestAddEmployeeDTO("Ivan","Ivanov","google@gmail.com","IT");


        when(employeeRepositoryDataBase.findByEmail(request.getEmail())).thenReturn(Optional.of(new Employee()));

        ResponceEmployeeDTO testResult = addEmployeeService.createEmployee(request);



        assertThrows(AlreadyExistException.class, () -> addEmployeeService.createEmployee(request));

    }

}
