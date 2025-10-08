package com.sergey.demoprojectspringboot.dto.dtoUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeOwnerDto {
    private String departmentName;
    private Integer employeeId;
    ;
}
