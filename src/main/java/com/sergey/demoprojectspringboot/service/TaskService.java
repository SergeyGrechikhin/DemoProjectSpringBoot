package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDto;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateTaskOwnerDto;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDto;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.AccessDeniedException;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.TaskConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepositoryDataBase taskRepositoryDataBase;
    private TaskConverter taskConverter;

    private EmployeeService employeeService;


    public ResponseTaskDTO createTask(RequestTaskDto request) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findTaskByTaskName(request.getTaskName());

        if (taskOptional.isPresent()) {
            throw new AlreadyExistException(" Task with this name Already Exist ");
        }

        Task task = Task.builder()
                .taskName(request.getTaskName())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .status(Task.Status.UNASSIGNED)
                .createDate(LocalDate.now())
                .updateDate(LocalDateTime.now())
                .employee(null)
                .build();

        if (task.getDeadline().isBefore(LocalDate.now())) {
            throw new BadRequestException("The deadline date cannot be in the past ");
        }

        Task saved = taskRepositoryDataBase.save(task);

        return taskConverter.toDto(saved);
    }

    public ResponseTaskDTO addTaskToEmployee(UpdateTaskOwnerDto request) {
        Optional<Employee> employeeOptional = employeeService.findByIdForService(request.getEmployeeId());
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(request.getTaskId());
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException("Employee with id " + request.getEmployeeId() + " not found ");
        }
        if (taskOptional.isEmpty()) {
            throw new NotFoundException("Task with id " + request.getTaskId() + " not found ");
        }
        Employee employeeForInvite = employeeOptional.get();

        Task taskForInvite = taskOptional.get();

        taskForInvite.setEmployee(employeeForInvite);

        taskForInvite.setStatus(Task.Status.TO_DO);

        taskForInvite.setUpdateDate(LocalDateTime.now());

        Task taskForSave = taskRepositoryDataBase.save(taskForInvite);

        return taskConverter.toDto(taskForSave);

    }

    public ResponseTaskDTO deleteTaskById(Integer id) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Task " + " with " + id + " id " + " not found ");
        }

        Task task = taskOptional.get();
        taskRepositoryDataBase.delete(task);

        return taskConverter.toDto(task);

    }

    public ResponseTaskDTO findById(Integer id) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Task with this" + id + " id not found ");
        } else {
            return taskConverter.toDto(taskOptional.get());
        }
    }

    public ResponseTaskDTO findByName(String name) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findTaskByTaskName(name);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Employee with this " + name + " email not found ");
        }
        return taskConverter.toDto(taskOptional.get());
    }

    public Optional<Task> findByIdForService(Integer id) {
        return taskRepositoryDataBase.findById(id);
    }

    public List<ResponseTaskDTO> getMyTasks() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepositoryDataBase.findByEmployee_Email(email)
                .stream()
                .map(taskConverter::toDto)
                .toList();
    }

    public ResponseTaskDTO updateTaskStatusByIdForAdmin(Integer id, UpdateStatusDto status) {
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Task with this " + id + " id not found ");
        }

        LocalDateTime now = LocalDateTime.now();


        Task task = taskForUpdate.get();
        task.setStatus(status.getStatus());
        task.setUpdateDate(now);

        taskRepositoryDataBase.save(task);

        return taskConverter.toDto(task);
    }

    public ResponseTaskDTO updateTaskDeadlineById(Integer id, LocalDate deadline) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Task with this " + id + " id not found ");
        }
        if (deadline.isBefore(LocalDate.now())) {
            throw new BadRequestException("The deadline date cannot be in the past ");
        }


        Task task = taskForUpdate.get();
        task.setDeadline(deadline);
        task.setUpdateDate(now);

        taskRepositoryDataBase.save(task);

        return taskConverter.toDto(task);
    }

    public ResponseTaskDTO updateTaskDescriptionById(Integer id, String description) {
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Task with this " + id + " id not found ");
        }
        LocalDateTime now = LocalDateTime.now();

        Task task = taskForUpdate.get();
        task.setDescription(description);
        task.setUpdateDate(now);

        taskRepositoryDataBase.save(task);

        return taskConverter.toDto(task);
    }

    public ResponseTaskDTO updateTaskStatusByIdForUser(Integer id, UpdateStatusDto status) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Task with this " + id + " id not found ");
        }
        if (!taskForUpdate.get().getEmployee().getEmail().equals(email)) {
            throw new AccessDeniedException("It is not your task . You cannot update this task");
        }
        LocalDateTime now = LocalDateTime.now();

        Task task = taskForUpdate.get();
        task.setStatus(status.getStatus());
        task.setUpdateDate(now);

        taskRepositoryDataBase.save(task);

        return taskConverter.toDto(task);


    }


}
