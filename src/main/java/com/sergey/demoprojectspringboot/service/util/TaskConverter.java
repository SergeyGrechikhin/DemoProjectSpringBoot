package com.sergey.demoprojectspringboot.service.util;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskConverter {
    public Task fromDto(RequestTaskDTO request){

        return Task.builder()
                .taskName(request.getTaskName())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .build();
    }

    public ResponceTaskDTO toDto(Task task){
        return ResponceTaskDTO.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .deadline(task.getDeadline())
                .status(task.getStatus().toString())
                .build();
    }

    public List<ResponceTaskDTO> fromTasks(List<Task> tasks){
        return tasks.stream()
                .map(task -> toDto(task))
                .toList();
    }
}
