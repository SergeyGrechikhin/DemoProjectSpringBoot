package com.sergey.demoprojectspringboot.controller.api;

import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDto;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateStatusDto;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin/update")
public interface AdminUpdateApi {
    @PutMapping("/updateName/{id}/{name}")
    ResponseEntity<ResponseEmployeeDTO> updateEmployeeNameById(@PathVariable Integer id, @PathVariable String name);


    @PutMapping("/updateSurname/{id}/{surname}")
    ResponseEntity<ResponseEmployeeDTO> updateEmployeeSurnameById(@PathVariable Integer id, @PathVariable String surname);


    @PutMapping("/updateEmail/{id}/{email}")
    ResponseEntity<ResponseEmployeeDTO> updateEmployeeEmailById(@PathVariable Integer id, @PathVariable String email);

    @PutMapping("/updateDepartmentName/{name}/{id}")
    ResponseEntity<ResponseDepartmentDTO> updateDepartmentNameById(@PathVariable String name, @PathVariable Integer id);

    @PutMapping("/updateDeadline/{id}/deadline")
    ResponseEntity<ResponseTaskDTO> updateTaskDeadlineById(@PathVariable Integer id, @RequestBody UpdateDeadlineDto request);


    @PutMapping("/updateDescription/{id}")
    ResponseEntity<ResponseTaskDTO> updateTaskDescriptionById(@PathVariable Integer id, @RequestBody String description);

    @PutMapping("/updateStatusForAdmin/{id}")
    ResponseEntity<ResponseTaskDTO> updateStatusForAdmin(@PathVariable Integer id, @RequestBody UpdateStatusDto status);


}
