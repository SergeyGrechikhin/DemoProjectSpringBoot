package com.sergey.demoprojectspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z._!-]+$", message = "Name can contain latin characters only")
    private String name;
    @NotBlank
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z._!-]+$", message = "Name can contain latin characters only")
    private String surname;
    @NotBlank
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    public Employee(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
