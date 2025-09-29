package com.sergey.demoprojectspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "The name field cannot be empty")
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z._!-]+$", message = "Use only Latin letters")
    private String name;
    @NotBlank(message = "The surname field cannot be empty" )
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z._!-]+$", message = "Use only Latin letters")
    private String surname;
    @NotBlank(message = "The email field cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid Email , use this format ***@***.***")
    private String email;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public enum Role {
        ADMIN,
        USER,
        MANAGER
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;




    public Employee(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setEmployee(this);
    }







}
