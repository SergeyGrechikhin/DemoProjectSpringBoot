package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tasks")
public interface TaskApi {

    @PutMapping("/updateStatusForUser/{id}")
    ResponseEntity<ResponseTaskDTO> updateStatusForUser(@PathVariable Integer id, @RequestBody UpdateStatusDTO status);

    @GetMapping("/getMyTask")
    ResponseEntity<List<ResponseTaskDTO>> getMyTask();


}
