package com.example.Lab7_Learning_Management_System.LMS.Controller;

import com.example.Lab7_Learning_Management_System.LMS.Api.ApiResponse;
import com.example.Lab7_Learning_Management_System.LMS.Model.Assignment;
import com.example.Lab7_Learning_Management_System.LMS.Service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAssignments() {
        return ResponseEntity.status(200).body(assignmentService.getAssignments());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAssignment(@RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        assignmentService.addAssignment(assignment);
        return ResponseEntity.status(201).body(new ApiResponse("Assignment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateAssignment(@PathVariable String id, @RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (assignmentService.updateAssignment(id, assignment)) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Assignment with id " + id + " not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAssignment(@PathVariable String id) {
        if (assignmentService.deleteAssignment(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Assignment with id " + id + " not found"));
    }


    @GetMapping("/check-grade/{id}/{score}")
    public ResponseEntity<ApiResponse> checkGrade(@PathVariable String id, @PathVariable double score) {
        int result = assignmentService.checkGrade(id, score);

        if (result == 1) {
            return ResponseEntity.status(404).body(new ApiResponse("Assignment with ID " + id + " not found."));
        }

        if (result == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid score: " + score + " exceeds the maximum allowed marks for this assignment."));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Grade " + score + " is valid and has been verified."));
    }



    @GetMapping("/get-hard")
    public ResponseEntity<?> getHardAssignments() {
        ArrayList<Assignment> hardList = assignmentService.getHardAssignments();
        if (hardList.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No hard assignments found (>50 marks)"));
        }
        return ResponseEntity.status(200).body(hardList);
    }
    @PutMapping("/extend-deadline/{id}/{extraDays}")
    public ResponseEntity<ApiResponse> extendDeadline(@PathVariable String id, @PathVariable int extraDays) {
        if (assignmentService.extendDeadline(id, extraDays)) {
            return ResponseEntity.status(200).body(new ApiResponse("Deadline extended successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Assignment with id "+id+" not found"));
    }
}