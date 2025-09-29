package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.service.addService.AddTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
class AddTaskServiceTest {

    @Autowired
    private AddTaskService addTaskService;

    @Test
    void createEmployee() {

        LocalDate now = LocalDate.now();

        RequestTaskDTO request = RequestTaskDTO.builder()
                .taskName("Task")
                .description("change code")
                .deadline(now)
                .employeeId(1)
                .build();

        addTaskService.createTask(request);

    }

}