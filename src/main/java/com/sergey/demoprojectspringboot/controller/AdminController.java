package com.sergey.demoprojectspringboot.controller;

import com.sergey.demoprojectspringboot.controller.api.AdminApi;
import com.sergey.demoprojectspringboot.dto.dtoUpdate.UpdateDeadlineDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestCreateDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.requestDto.RequestTaskDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceDepartmentDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import com.sergey.demoprojectspringboot.service.addService.AddDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddEmployeeToDepartmentService;
import com.sergey.demoprojectspringboot.service.addService.AddTaskService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteDepartmentService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteEmployeeService;
import com.sergey.demoprojectspringboot.service.deleteService.DeleteTaskService;
import com.sergey.demoprojectspringboot.service.findService.FindDepartmentService;
import com.sergey.demoprojectspringboot.service.findService.FindEmployeeService;
import com.sergey.demoprojectspringboot.service.findService.FindTaskService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateDepartmentService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateEmployeeService;
import com.sergey.demoprojectspringboot.service.updateService.UpdateTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor

public class AdminController implements AdminApi {
    private final FindEmployeeService findEmployeeService;
    private final DeleteEmployeeService deleteEmployeeService;
    private final UpdateEmployeeService updateEmployeeService;
    private final FindDepartmentService findDepartmentService;
    private final AddDepartmentService addDepartmentService;
    private final AddEmployeeToDepartmentService addEmployeeToDepartmentService;
    private final DeleteDepartmentService deleteDepartmentService;
    private final UpdateDepartmentService updateDepartmentService;
    private final FindTaskService findTaskService;
    private final AddTaskService addTaskService;
    private final UpdateTaskService updateTaskService;
    private final DeleteTaskService deleteTaskService;


    @Override
    public ResponseEntity<List<ResponceEmployeeDTO>> findAllEmployees() {
        return ResponseEntity.ok(findEmployeeService.findAll());
    }

    @Override
    public ResponseEntity<List<ConfirmationCode>> findAllEmployeesCodes(String email) {
        return ResponseEntity.ok(findEmployeeService.findCodesByEmployee(email));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> updateEmployeeName(Integer id, String name) {
        return ResponseEntity.ok(updateEmployeeService.updateEmployeeNameById(id, name));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> updateEmployeeSurname(Integer id, String surname) {
        return ResponseEntity.ok(updateEmployeeService.updateEmployeeSurnameById(id, surname));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> updateEmployeeEmail(Integer id, String email) {
        return ResponseEntity.ok(updateEmployeeService.updateEmployeeEmailById(id, email));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> deleteEmployeeById(Integer IdForDelete) {
        return ResponseEntity.ok(deleteEmployeeService.deleteEmployeeById(IdForDelete));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> findEmployeeById(Integer id) {
        return ResponseEntity.ok(findEmployeeService.getUserById(id));
    }

    @Override
    public ResponseEntity<List<ResponceEmployeeDTO>> findEmployeeByName(String name) {
        return ResponseEntity.ok(findEmployeeService.findByName(name));
    }

    @Override
    public ResponseEntity<List<ResponceEmployeeDTO>> findEmployeeBySurname(String surname) {
        return ResponseEntity.ok(findEmployeeService.findBySurname(surname));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> findEmployeeByEmail(String email) {
        return ResponseEntity.ok(findEmployeeService.findByEmail(email));
    }

    @Override
    public ResponseEntity<ResponceDepartmentDTO> deleteDepartmentById(Integer idForDelete) {
        return ResponseEntity.ok(deleteDepartmentService.deleteDepartment(idForDelete));
    }

    @Override
    public ResponseEntity<ResponceDepartmentDTO> updateDepartmentName(String name, Integer id) {
        return ResponseEntity.ok(updateDepartmentService.updateDepartmentNameById(name, id));
    }

    @Override
    public ResponseEntity<List<ResponceDepartmentDTO>> findDepartmentAll() {
        return ResponseEntity.ok(findDepartmentService.findAll());
    }

    @Override
    public ResponseEntity<ResponceDepartmentDTO> findDepartmentById(Integer id) {
        return ResponseEntity.ok(findDepartmentService.findById(id));
    }

    @Override
    public ResponseEntity<ResponceDepartmentDTO> findDepartmentByName(String name) {
        return ResponseEntity.ok(findDepartmentService.findByName(name));
    }

    @Override
    public ResponseEntity<List<ResponceEmployeeDTO>> findEmployeesByDepartmentName(String name) {
        return ResponseEntity.ok(findDepartmentService.getEmployeesFromDepartment(name));
    }

    @Override
    public ResponseEntity<ResponceDepartmentDTO> addNewDepartment(RequestCreateDepartmentDTO request) {
        return ResponseEntity.ok(addDepartmentService.create(request));
    }

    @Override
    public ResponseEntity<ResponceEmployeeDTO> addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        return ResponseEntity.ok(addEmployeeToDepartmentService.addEmployeeToAnotherDepartment(departmentId, employeeId));
    }

    @Override
    public ResponseEntity<ResponceTaskDTO> addTask(RequestTaskDTO request) {
        return ResponseEntity.ok(addTaskService.createTask(request));
    }

    @Override
    public ResponseEntity<ResponceTaskDTO> updateDeadline(Integer id, UpdateDeadlineDTO request) {
        return ResponseEntity.ok(updateTaskService.updateTaskDeadlineById(id,request.getDeadline()));
    }

    @Override
    public ResponseEntity<ResponceTaskDTO> updateDescription(Integer id, String description) {
        return ResponseEntity.ok(updateTaskService.updateTaskDescriptionById(id, description));
    }

    @Override
    public ResponseEntity<ResponceTaskDTO> deleteTaskById(Integer IdForDelete) {
        return ResponseEntity.ok(deleteTaskService.deleteTaskById(IdForDelete));
    }
}
