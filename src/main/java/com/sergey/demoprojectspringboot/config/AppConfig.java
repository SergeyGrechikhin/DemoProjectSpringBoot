package com.sergey.demoprojectspringboot.config;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestEmployeeDto;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class AppConfig {
    public static final String ADMIN_EMAIL = "admin@example.com";
    public static final String ADMIN_PASSWORD = "admin123";
    public static final String ADMIN_USERNAME = "Admin";
    public static final String ADMIN_SURNAME = "Root";
    private final EmployeeService employeeService;

    @Bean
    public CommandLineRunner lineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) {
                try {
                    employeeService.getUserByEmailOrThrow(ADMIN_EMAIL);
                } catch (NotFoundException e) {
                    employeeService.employeeRegistration(new RequestEmployeeDto(ADMIN_USERNAME, ADMIN_SURNAME, ADMIN_EMAIL, ADMIN_PASSWORD));
                    Employee employeeAdmin = employeeService.getUserByEmailOrThrow(ADMIN_EMAIL);
                    employeeService.setConfirmedAdmin(employeeAdmin);

                }
            }
        };
    }

}
