package com.sergey.demoprojectspringboot.service;

import com.sergey.demoprojectspringboot.dto.RequestAddEmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ValidationService {


    public static final Pattern LATIN_PATTERN = Pattern.compile("^[A-Za-z._!-]+$");

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");


}
