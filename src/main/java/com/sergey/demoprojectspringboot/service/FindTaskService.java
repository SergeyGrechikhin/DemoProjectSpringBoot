package com.sergey.demoprojectspringboot.service;


import com.sergey.demoprojectspringboot.dto.ResponseTaskDTO;

import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class FindTaskService {

    private TaskRepositoryDataBase taskRepositoryDataBase;
    //private TaskConverter taskConverter ;

    public ResponseTaskDTO findById(Integer id) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Task with this" + id + " id not found ");
        } else {
            return ResponseTaskDTO.toDTO(taskOptional.get());
        }
    }

    public ResponseTaskDTO findByName(String name) {
        Optional<Task> taskOptional = taskRepositoryDataBase.findTaskByTaskName(name);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(" Employee with this " + name + " email not found ");
        }
        return ResponseTaskDTO.toDTO(taskOptional.get());
    }

    public Optional<Task> findByIdForService(Integer id) {
        return taskRepositoryDataBase.findById(id);
    }
}
