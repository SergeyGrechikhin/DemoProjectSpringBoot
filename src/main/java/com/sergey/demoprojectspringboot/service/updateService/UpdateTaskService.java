package com.sergey.demoprojectspringboot.service.updateService;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
@AllArgsConstructor

public class UpdateTaskService {
    private TaskRepositoryDataBase taskRepositoryDataBase;

    public ResponceTaskDTO updateTaskStatusById(Integer id, UpdateStatusDTO status) {
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Task with this " + id + " id not found ");
        }

        LocalDateTime now = LocalDateTime.now();




        Task task = taskForUpdate.get();
        task.setStatus(status.getStatus());
        task.setUpdateDate(now);

        taskRepositoryDataBase.save(task);

        return ResponceTaskDTO.toDTO(task);
    }

    public ResponceTaskDTO updateTaskDeadlineById(Integer id, LocalDate deadline) {
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

        return ResponceTaskDTO.toDTO(task);
    }

    public ResponceTaskDTO updateTaskDescriptionById(Integer id, String description) {
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Task with this " + id + " id not found ");
        }
        LocalDateTime now = LocalDateTime.now();

        Task task = taskForUpdate.get();
        task.setDescription(description);
        task.setUpdateDate(now);

        taskRepositoryDataBase.save(task);

        return ResponceTaskDTO.toDTO(task);
    }


}
