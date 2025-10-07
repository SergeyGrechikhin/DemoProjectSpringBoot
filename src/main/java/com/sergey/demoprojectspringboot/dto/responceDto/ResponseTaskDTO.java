package com.sergey.demoprojectspringboot.dto.responceDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTaskDTO {
    private Integer id;
    private String taskName;
    private String description;
    private LocalDate createDate;
    private LocalDate deadline;
    private String status;




}
