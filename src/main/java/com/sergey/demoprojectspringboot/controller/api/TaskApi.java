package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/tasks")
public interface TaskApi {

    @PutMapping("/updateStatus/{id}/{status}")
    ResponseEntity<ResponceTaskDTO> updateStatus(@PathVariable Integer id, @PathVariable Task.Status status);

}
