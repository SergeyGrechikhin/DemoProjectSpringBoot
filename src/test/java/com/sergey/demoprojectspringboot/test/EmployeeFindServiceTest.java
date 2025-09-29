package com.sergey.demoprojectspringboot.test;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeService;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

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


        when(employeeRepository.findBySurname(searchSurname)).thenReturn(List.of(employee1));

        List<ResponceEmployeeDTO> result = findEmployeeService.findBySurname("Ivanov");

        assertEquals("Ivanov", result.get(0).getName());
        assertEquals("Ivanov", result.get(1).getName());
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
