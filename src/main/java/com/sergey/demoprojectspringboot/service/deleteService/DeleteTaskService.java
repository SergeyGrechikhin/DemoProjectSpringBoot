package com.sergey.demoprojectspringboot.service.deleteService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.TaskConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteTaskService {

    private TaskRepositoryDataBase taskRepositoryDataBase;
    private TaskConverter taskConverter;

    public ResponseTaskDTO deleteTaskById(Integer id) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Task " + " with " + id + " id " + " not found ");
        }

        Task task = taskOptional.get();
        taskRepositoryDataBase.delete(task);

        return taskConverter.toDto(task);

    }
}
