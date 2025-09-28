package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.ResponseTaskDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteTaskService {

    private TaskRepositoryDataBase taskRepositoryDataBase;

    public ResponseTaskDTO deleteEmployeeById(Integer id) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Task " + " with " + id + " id " + " not found ");
        }

        Task task = taskOptional.get();
        taskRepositoryDataBase.delete(task);

        return ResponseTaskDTO.toDTO(task);

    }
}
