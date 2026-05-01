package com.example.Lab7_Learning_Management_System.LMS.Controller;


import com.example.Lab7_Learning_Management_System.LMS.Api.ApiResponse;
import com.example.Lab7_Learning_Management_System.LMS.Model.Course;
import com.example.Lab7_Learning_Management_System.LMS.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getCourses() {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (courseService.updateCourse(id, course)) {
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course not with id "+id+" found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable String id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course not with id "+id+" found"));
    }

    @GetMapping("/enroll/{sLevel}/{cLevel}")
    public ResponseEntity<ApiResponse> enrollStudent(@PathVariable int sLevel, @PathVariable int cLevel) {
        if (courseService.enrollStudent(sLevel, cLevel) == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("Student enrolled successfully, levels match!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Levels do not match, cannot enroll"));
    }

    @GetMapping("/get-available")
    public ResponseEntity<?> getAvailableCourses() {
        if (courseService.getAvailableCourses().isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No available courses found"));
        }
        return ResponseEntity.status(200).body(courseService.getAvailableCourses());
    }
    @GetMapping("/get-by-range/{min}/{max}")
    public ResponseEntity<?> getCoursesByLevelRange(@PathVariable int min, @PathVariable int max) {
        ArrayList<Course> filtered = courseService.getCoursesByLevelRange(min, max);
        if (filtered.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No courses found in this level range"));
        }
        return ResponseEntity.status(200).body(filtered);
    }
}