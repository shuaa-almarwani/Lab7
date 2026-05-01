package com.example.Lab7_Learning_Management_System.LMS.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Assignment {
    @NotEmpty(message = "ID cannot be null")
    private String ID;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Total Cannot be empty")
    @Positive(message = "Total marks must be positive")
    @Max(value = 100, message = "Total marks maximum is 100")
    private double totalMarks;
    @Positive(message = "Deadline must be positive")
    private int deadlineInDays;
}