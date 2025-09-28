package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddTaskService {
    private TaskRepositoryDataBase taskRepositoryDataBase;

    private FindEmployeeService findEmployeeService;
    private AddTaskToEmployeeService addTaskToEmployeeService;

    public ResponseTaskDTO createTask(RequestTaskDTO request) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findTaskByTaskName(request.getTaskName());
        Optional<Employee> employeeOptional = findEmployeeService.findByIdForService(request.getEmployeeId());


        if (taskOptional.isPresent()) {
            throw  new AlreadyExistException(" Task with this name Already Exist ");
        }

        Task task = Task.builder()
                .taskName(request.getTaskName())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .status(Task.Status.TO_DO)
                .createDate(LocalDate.now())
                .build();

        task.setEmployee(employeeOptional.get());

        Task saved = taskRepositoryDataBase.save(task);
        return ResponseTaskDTO.toDTO(saved);
    }
}
