package com.sergey.demoprojectspringboot.service.deleteService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteTaskService {

    private TaskRepositoryDataBase taskRepositoryDataBase;

    public ResponceTaskDTO deleteTaskById(Integer id) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Task " + " with " + id + " id " + " not found ");
        }

        Task task = taskOptional.get();
        taskRepositoryDataBase.delete(task);

        return ResponceTaskDTO.toDTO(task);

    }
}
