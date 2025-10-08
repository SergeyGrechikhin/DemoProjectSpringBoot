package com.sergey.demoprojectspringboot.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTaskDto {
    private String taskName;
    private String description;
    private LocalDate deadline;

}
