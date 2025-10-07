package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.dto.responceDto.ResponceTaskDTO;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryDataBase extends JpaRepository<Task, Integer> {

    Optional<Task> findTaskByTaskName(String taskName);

    List<Task> findByEmployee_Email(String employeeEmail);

    boolean existsByIdAndEmployee_Email(Integer id, String employeeEmail);


}
