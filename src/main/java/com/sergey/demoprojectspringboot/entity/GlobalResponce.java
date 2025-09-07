package com.sergey.demoprojectspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponce <T> {
    private HttpStatus status;
    private T object;

}
