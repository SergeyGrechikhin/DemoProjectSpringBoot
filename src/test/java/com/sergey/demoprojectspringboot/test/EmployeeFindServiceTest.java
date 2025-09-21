package com.sergey.demoprojectspringboot.test;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeFindServiceTest {

    @Mock
    private EmployeeRepositoryDataBase employeeRepository;

    @InjectMocks
    private FindEmployeeService findEmployeeService;


    @InjectMocks
    private AddEmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateEmployeeAlreadyExist() {

        RequestAddEmployeeDTO request = new RequestAddEmployeeDTO("Ruslan", "Ivanov", "google5@gmail.com", "IT");

        when(employeeRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Employee()));

        assertThrows(AlreadyExistException.class, () -> employeeService.createEmployee(request));
    }


    @Test
    void testGetUserById() {

        Integer searchId = 1;
        ResponceEmployeeDTO responceEmployeeDTO = new ResponceEmployeeDTO(1, "Ivanov", "Ivanov", "IT");

        when(employeeRepository.findById(searchId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> findEmployeeService.findById(searchId));


    }

    @Test
    void testGetUserByName() {

        String searchName = "ivan";
        Employee employee1 = new Employee("Ruslan", "Ivanov", "google@gmail.com");
        Employee employee2 = new Employee("Ivan", "Ruslanov", "google1@gmail.com");
        Employee employee3 = new Employee("Ivan", "Maximov", "google2@gmail.com");

        when(employeeRepository.findByNameContainingIgnoreCase(searchName)).thenReturn(List.of(employee2, employee3));

        List<ResponceEmployeeDTO> result = findEmployeeService.findByName(searchName);

        assertEquals(2, result.size());
        assertEquals("Ivan", result.get(0).getName());
        assertEquals("Ivan", result.get(1).getName());

    }

    @Test
    void testGetUserBySurname() {

        String searchSurname = "Ivanov";
        Employee employee1 = new Employee("Ruslan", "Ivanov", "google@gmail.com");
        Employee employee2 = new Employee("Ivan", "Ruslanov", "google1@gmail.com");


        when(employeeRepository.findBySurname(searchSurname)).thenReturn(List.of(employee1, employee2));

        List<ResponceEmployeeDTO> result = findEmployeeService.findBySurname("Ivanov");

        assertNotNull(result);
        assertEquals(1, result.size());


        verify(employeeRepository).findBySurname(searchSurname);


    }


    @Test
    void testGetUserByEmail() {

        String searchEmail = "google@gmail.com";

        when(employeeRepository.findByEmail(searchEmail)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> findEmployeeService.findByEmail(searchEmail));

    }


}
