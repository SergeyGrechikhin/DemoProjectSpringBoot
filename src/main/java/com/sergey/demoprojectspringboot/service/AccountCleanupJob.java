package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountCleanupJob {

    private final EmployeeRepositoryDataBase employeeRepositoryDataBase;
    private final JdbcTemplate jdbcTemplate;
    private final CodeConfirmationService codeConfirmationService;

    @Scheduled(fixedRate = 30000)     //(cron = "0 0 3 * * *")
    public void deleteInactiveAfter14Days() {
//        LocalDateTime deleteDate = LocalDateTime.now().minusDays(14);

        LocalDateTime deleteDate = LocalDateTime.now().minusSeconds(30);


        List<Employee> inactiveEmployees = employeeRepositoryDataBase.findByStatusAndDeactivateAtBefore(Employee.Status.INACTIVE, deleteDate);

        for (Employee employee : inactiveEmployees) {

            codeConfirmationService.deleteConfirmationCode(employee);

            employeeRepositoryDataBase.delete(employee);
        }




    }
}


//old info
//
//        String sqlDeleteCodes = """
//                    DELETE cc FROM confirmation_code cc
//                    JOIN employees e ON cc.user_id = e.id
//                    WHERE e.status = 'INACTIVE' AND e.deactivate_at < ?
//                """;
//
//        int deletedCodes = jdbcTemplate.update(sqlDeleteCodes, deleteDate);
//        long deletedEmployees = employeeRepositoryDataBase
//                .deleteByStatusAndDeactivateAtBefore(Employee.Status.INACTIVE, deleteDate);
//
//        log.info("Deleted {} codes and {} employees", deletedEmployees, deletedCodes);

