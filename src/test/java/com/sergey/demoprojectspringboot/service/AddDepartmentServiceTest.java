package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.repository.DepartmentRepositoryDataBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
class AddDepartmentServiceTest {


    @Autowired
    private DepartmentRepositoryDataBase departmentRepository;

    @Autowired
    private AddDepartmentService addDepartmentService;

    @Test
    void addDepartmentTest() {

        RequestCreateDepartmentDTO requestCreateDepartmentDTO = RequestCreateDepartmentDTO.builder()
                .name("IT")
                .build();

        addDepartmentService.create(requestCreateDepartmentDTO);

    }

}