package com.example.Lab7_Learning_Management_System.LMS.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
        @NotEmpty(message = "ID cannot be null")
        private String ID;

        @NotEmpty(message = "Name cannot be empty")
        private String name;

        @Email(message = "Must be a valid email format")
        @NotEmpty(message = "Email cannot be empty")
        private String email;

        @NotNull(message = "Level cannot be null")
        @Min(value = 1, message = "Level must be between 1 and 8")
        @Max(value = 8, message = "Level must be between 1 and 8")
        private int level;
    }

