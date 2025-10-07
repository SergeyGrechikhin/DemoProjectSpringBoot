package com.sergey.demoprojectspringboot.service.findService;


import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;

import com.sergey.demoprojectspringboot.entity.Task;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.TaskRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.TaskConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindTaskService {

    private TaskRepositoryDataBase taskRepositoryDataBase;
    private TaskConverter taskConverter;


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


}
