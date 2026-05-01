package com.example.Lab7_Learning_Management_System.LMS.Controller;

import com.example.Lab7_Learning_Management_System.LMS.Api.ApiResponse;
import com.example.Lab7_Learning_Management_System.LMS.Model.Teacher;
import com.example.Lab7_Learning_Management_System.LMS.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<?> getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body(new ApiResponse("Teacher added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateTeacher(@PathVariable String id, @RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (teacherService.updateTeacher(id, teacher)) {
            return ResponseEntity.status(200).body(new ApiResponse("Teacher updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Teacher with id " + id + " not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable String id) {
        if (teacherService.deleteTeacher(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Teacher with id " + id + " not found"));
    }

}