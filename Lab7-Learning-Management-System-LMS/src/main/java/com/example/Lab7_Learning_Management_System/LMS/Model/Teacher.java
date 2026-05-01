package com.example.Lab7_Learning_Management_System.LMS.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "ID cannot be null")
    private String ID;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Positive(message = "Salary must be a positive number")
    private double salary;

    @NotEmpty(message = "Subject cannot be empty")
    private String subject;
}