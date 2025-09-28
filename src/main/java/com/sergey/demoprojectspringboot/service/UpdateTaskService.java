package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor

public class UpdateTaskService {
    private TaskRepositoryDataBase taskRepositoryDataBase;

    public ResponceTaskDTO updateTaskStatusById(Integer id, Task.Status status) {
        Optional<Task> taskForUpdate = taskRepositoryDataBase.findById(id);
        if (taskForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }



        Task task = taskForUpdate.get();
        task.setStatus(status);

        taskRepositoryDataBase.save(task);

        return ResponceTaskDTO.toDTO(task);
    }


}
