package com.sergey.demoprojectspringboot.service.util;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
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

    public ResponseTaskDTO toDto(Task task){
        return ResponseTaskDTO.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .deadline(task.getDeadline())
                .status(task.getStatus().toString())
                .build();
    }

    public List<ResponseTaskDTO> fromTasks(List<Task> tasks){
        return tasks.stream()
                .map(task -> toDto(task))
                .toList();
    }
}
