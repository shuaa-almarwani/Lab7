package com.example.Lab7_Learning_Management_System.LMS.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "ID cannot be null")
    private String ID;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 5, message = "Title must be at least 5 characters")
    private String title;

    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be Not Started, In Progress, or Completed")
    private String status;

    @NotEmpty(message = "Description cannot be empty")
    private String description;
}