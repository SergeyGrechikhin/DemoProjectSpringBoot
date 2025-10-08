package com.sergey.demoprojectspringboot.dto.dtoUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDeadlineDto {
    private LocalDate deadline;
}
