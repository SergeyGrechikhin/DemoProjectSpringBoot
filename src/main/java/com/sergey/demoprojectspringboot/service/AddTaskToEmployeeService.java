package com.sergey.demoprojectspringboot.service;


import com.sergey.demoprojectspringboot.dto.ResponceTaskDTO;

import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddTaskToEmployeeService {
    private TaskRepositoryDataBase taskRepositoryDataBase;
    private FindEmployeeService findEmployeeService;
    private FindTaskService findTaskService;

    public ResponceTaskDTO addTaskToEmployee(Employee employee, Task task) {
        Optional<Employee> employeeOptional = findEmployeeService.findByIdForService(employee.getId());
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException("Employee with id " + employee.getId() + " not found ");
        }
        Employee employeeForInvite = employeeOptional.get();

        task.setEmployee(employeeForInvite);

        employeeForInvite.getTasks().add(task);

        Task taskforSave = taskRepositoryDataBase.save(task);

        return ResponceTaskDTO.toDTO(taskforSave);

    }
}
