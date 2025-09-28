package com.sergey.demoprojectspringboot.dto;

import com.sergey.demoprojectspringboot.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponceTaskDTO {
    private Integer id;
    private String taskName;
    private String description;
    private LocalDate createDate;
    private LocalDate deadline;
    private String status;

    public static ResponceTaskDTO toDTO(Task task){
        return new ResponceTaskDTO(task.getId(), task.getTaskName(),task.getDescription(),task.getCreateDate(),task.getDeadline(),task.getStatus().toString());
    }


}
