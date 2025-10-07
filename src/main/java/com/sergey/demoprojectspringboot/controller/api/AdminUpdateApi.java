package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/update")
public interface AdminUpdateApi {
    @PutMapping("/updateName/{id}/{name}")
    ResponseEntity<ResponceEmployeeDTO> updateEmployeeNameById(@PathVariable Integer id, @PathVariable String name);


    @PutMapping("/updateSurname/{id}/{surname}")
    ResponseEntity<ResponceEmployeeDTO> updateEmployeeSurnameById(@PathVariable Integer id, @PathVariable String surname);


    @PutMapping("/updateEmail/{id}/{email}")
    ResponseEntity<ResponceEmployeeDTO> updateEmployeeEmailById(@PathVariable Integer id, @PathVariable String email);

    @PutMapping("/updateDepartmentName/name/{name}/{id}")
    ResponseEntity<ResponceDepartmentDTO> updateDepartmentNameById(@PathVariable String name, @PathVariable Integer id);

    @PutMapping("/updateDeadline/{id}/deadline")
    ResponseEntity<ResponceTaskDTO> updateTaskDeadlineById(@PathVariable Integer id, @RequestBody UpdateDeadlineDTO request);


    @PutMapping("/updateDescription/{id}/{description}")
    ResponseEntity<ResponceTaskDTO> updateTaskDescriptionById(@PathVariable Integer id, @PathVariable String description);


}
