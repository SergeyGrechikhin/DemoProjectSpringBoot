package com.sergey.demoprojectspringboot.controller.api;


import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/api/public")
public interface RegApi {

    @PostMapping("/reg")
    ResponseEntity<ResponceEmployeeDTO> employeeRegistration(@RequestBody RequestAddEmployeeDTO request);


}
