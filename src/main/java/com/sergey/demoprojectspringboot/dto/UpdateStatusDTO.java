package com.sergey.demoprojectspringboot.dto;

import com.sergey.demoprojectspringboot.entity.Task;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusDTO {
    @NotNull(message = "Status is required. One of : TO_DO , FOR_CHECK, COMPLETED, OVERDUE")
    private Task.Status status;
}
