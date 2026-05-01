package com.example.Lab7_Learning_Management_System.LMS.Controller;

import com.example.Lab7_Learning_Management_System.LMS.Api.ApiResponse;
import com.example.Lab7_Learning_Management_System.LMS.Model.Project;
import com.example.Lab7_Learning_Management_System.LMS.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping("/get")
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.status(200).body(projectService.getProjects());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        projectService.addProject(project);
        return ResponseEntity.status(201).body(new ApiResponse("Project added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProject(@PathVariable String id, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (projectService.updateProject(id, project)) {
            return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Project with id "+id+" not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable String id) {
        if (projectService.deleteProject(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Project with id " + id + " not found"));
    }




    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchByTitle(@PathVariable String title) {
        ArrayList<Project> results = projectService.searchByTitle(title);
        if (results.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No projects found with title: " + title));
        }
        return ResponseEntity.status(200).body(results);
    }
    @GetMapping("/get-by-status/{status}")
    public ResponseEntity<?> getProjectsByStatus(@PathVariable String status) {
        ArrayList<Project> results = projectService.getProjectsByStatus(status);
        if (!(status.equals("Not Started") || status.equals("In Progress") || status.equals("Completed"))) {
            return ResponseEntity.status(400).body(new ApiResponse("Status must be: Not Started, In Progress, or Completed"));
        }
        if (results.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No projects found with status: " + status));
        }
        return ResponseEntity.status(200).body(results);
    }

    @PutMapping("/update-status/{id}/{newStatus}")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable String id, @PathVariable String newStatus) {
        int result = projectService.updateStatus(id, newStatus);

        if (result == 1) {
            return ResponseEntity.ok(new ApiResponse("Status updated successfully"));
        }

        if (result == 0) {
            return ResponseEntity.status(404).body(new ApiResponse("Project not found"));
        }

        if (result == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid status name. Use: Not Started, In Progress, or Completed"));
        }


        if (result == 4) {
            return ResponseEntity.status(400).body(new ApiResponse("Cannot skip directly to 'Completed'. Project must be 'In Progress' first"));
        }

        if (result == 5) {
            return ResponseEntity.status(400).body(new ApiResponse("Cannot reset a 'Completed' project back to 'Not Started'"));
        }

        if (result == 6) {
            return ResponseEntity.status(400).body(new ApiResponse("Project is already in progress, you cannot return it to 'Not Started'"));
        }

        if (result == 3) {
            return ResponseEntity.status(400).body(new ApiResponse("This project is already completed and cannot be modified"));
        }

        return ResponseEntity.status(500).body(new ApiResponse("Internal Server Error"));
    }
}