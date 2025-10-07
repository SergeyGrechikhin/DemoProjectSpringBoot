package com.sergey.demoprojectspringboot.service.addService;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import com.sergey.demoprojectspringboot.service.util.TaskConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddTaskService {
    private TaskRepositoryDataBase taskRepositoryDataBase;
    private TaskConverter taskConverter;

    private FindEmployeeService findEmployeeService;
    private AddTaskToEmployeeService addTaskToEmployeeService;

    public ResponceTaskDTO createTask(RequestTaskDTO request) {
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
                .updateDate(LocalDateTime.now())
                .build();

        if (task.getDeadline().isBefore(LocalDate.now())) {
            throw new BadRequestException("The deadline date cannot be in the past ");
        }

        task.setEmployee(employeeOptional.get());

        Task saved = taskRepositoryDataBase.save(task);

        return taskConverter.toDto(saved);
    }
}
