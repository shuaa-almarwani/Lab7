package com.example.Lab7_Learning_Management_System.LMS.Controller;

import com.example.Lab7_Learning_Management_System.LMS.Api.ApiResponse;
import com.example.Lab7_Learning_Management_System.LMS.Model.Student;
import com.example.Lab7_Learning_Management_System.LMS.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/get")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("Student added successfully "));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (studentService.updateStudent(id, student)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student with id " + id + " not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable String id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student with id " + id + " not found"));
    }


    @GetMapping("/check-graduation/{id}")
    public ResponseEntity<ApiResponse> checkGraduation(@PathVariable String id) {
        String status = studentService.checkGraduation(id);
        if (status != null) {
            return ResponseEntity.status(200).body(new ApiResponse("Status: " + status));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student with id " + id + " not found"));
    }
}