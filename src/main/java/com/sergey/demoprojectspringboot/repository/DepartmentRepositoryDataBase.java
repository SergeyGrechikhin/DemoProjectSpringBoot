package com.sergey.demoprojectspringboot.repository;

import com.sergey.demoprojectspringboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;


public interface DepartmentRepositoryDataBase extends JpaRepository<Department, Integer> {

    Optional<Department> findByName(String name);


}
