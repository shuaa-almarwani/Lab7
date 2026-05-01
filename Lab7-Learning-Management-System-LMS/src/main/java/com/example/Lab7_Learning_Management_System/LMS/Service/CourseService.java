package com.example.Lab7_Learning_Management_System.LMS.Service;

import com.example.Lab7_Learning_Management_System.LMS.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean updateCourse(String id, Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equals(id)) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equals(id)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public int enrollStudent(int studentLevel, int courseLevel) {
        if (studentLevel == courseLevel) {
            return 1;
        }
        return 0;
    }

    public ArrayList<Course> getAvailableCourses() {
        ArrayList<Course> available = new ArrayList<>();
        for (Course c : courses) {
            if (c.isAvailable()) {
                available.add(c);
            }
        }
        return available;
    }

    public ArrayList<Course> getCoursesByLevelRange(int min, int max) {
        ArrayList<Course> filteredCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getLevel() >= min && c.getLevel() <= max) {
                filteredCourses.add(c);
            }
        }
        return filteredCourses;
    }
}