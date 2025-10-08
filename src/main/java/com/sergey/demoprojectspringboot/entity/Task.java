package com.sergey.demoprojectspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "The name of Task field cannot be empty")
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^[A-Za-z._!-]+$", message = "Use only Latin letters")
    private String taskName;
    @NotBlank(message = "The description field cannot be empty" )
    @Size(min = 1, max = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = true)
    private Employee employee;

    public enum Status {
        TO_DO,
        COMPLETED,
        FOR_CHECK,
        OVERDUE
    }
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;



    private LocalDate createDate;

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Europa/Berlin")
    private LocalDateTime updateDate;


    @NotNull(message ="The data of deadline cannot be empty ")
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europa/Berlin")
    private LocalDate deadline;


    public Task( String taskName,String description ,LocalDate deadline) {
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
    }


}
