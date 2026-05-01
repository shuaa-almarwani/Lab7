package com.example.Lab7_Learning_Management_System.LMS.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Course {
    @NotEmpty(message = "ID cannot be null")
    @Size(min = 3, message = "ID size must be more than 2")
    private String ID;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Level cannot be null")
    @Min(value = 1, message = "Level must be between 1 and 8")
    @Max(value = 8, message = "Level must be between 1 and 8")
    private int level;

    @NotEmpty(message = "Language cannot be empty")
    private String language;

    private boolean isAvailable = false;
}