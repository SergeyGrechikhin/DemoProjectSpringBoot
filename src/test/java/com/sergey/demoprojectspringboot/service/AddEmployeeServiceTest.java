package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeToDepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
class AddEmployeeServiceTest {

    @Autowired
    private AddEmployeeService addEmployeeService;

    @Autowired
    private EmployeeRepositoryDataBase employeeRepositoryDataBase;

    @Autowired
    private AddEmployeeToDepartmentService addEmployeeToDepartmentService;

    @Autowired
    private FindDepartmentService findDepartmentService;


    @Test
    void createEmployee() {

        RequestAddEmployeeDTO request = RequestAddEmployeeDTO.builder()
                .name("Max")
                .surname("Lobov")
                .email("googleforCheck@gmail.com")
                .departmentName("IT")
                .build();

        addEmployeeService.employeeRegistration(request);

    }


    @Test
    void testWhenDuplicatedEmail(){
        RequestAddEmployeeDTO request = RequestAddEmployeeDTO.builder()
                .name("user")
                .surname("user")
                .email("google77@gmail.com")
                .departmentName("Department 1")
                .build();

        assertThrows(AlreadyExistException.class, () -> addEmployeeService.employeeRegistration(request));
    }

}