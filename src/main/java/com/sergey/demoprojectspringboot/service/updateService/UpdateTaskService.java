package com.sergey.demoprojectspringboot.service.updateService;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.AccessDeniedException;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.TaskConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
@AllArgsConstructor

public class UpdateTaskService {
    private TaskRepositoryDataBase taskRepositoryDataBase;
    private TaskConverter taskConverter;

    public ResponseTaskDTO updateTaskStatusByIdForAdmin(Integer id, UpdateStatusDTO status) {
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

    public ResponseTaskDTO updateTaskStatusByIdForUser(Integer id, UpdateStatusDTO status) {
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
