package com.sergey.demoprojectspringboot.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthMeResponse {
    String name;
    String role;
    String status;
}
