package com.sergey.demoprojectspringboot.service.deleteService;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor

public class DeleteEmployeeService {

    private EmployeeRepositoryDataBase employeeRepository;
    private Converter converter;

    public ResponceEmployeeDTO deleteEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException(" Employee " + " with " + id + " id " + " not found ");
        }
        if(employeeOptional.get().getId() == 1){
            throw new BadRequestException("Employee " + " with " + id + " id " + " cannot be deleted");
        }

        Employee employee = employeeOptional.get();
        employeeRepository.delete(employee);

        return converter.toDto(employee);

    }
}
