package com.sergey.demoprojectspringboot.controller.api;


import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDto;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/api/public")
public interface RegApi {

    @PostMapping("/reg")
    ResponseEntity<ResponseEmployeeDTO> employeeRegistration(@RequestBody RequestAddEmployeeDto request);


}
