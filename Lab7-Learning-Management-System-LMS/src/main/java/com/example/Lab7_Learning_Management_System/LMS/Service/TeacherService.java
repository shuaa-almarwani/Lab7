package com.example.Lab7_Learning_Management_System.LMS.Service;

import com.example.Lab7_Learning_Management_System.LMS.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();


    public ArrayList<Teacher> getTeachers() { return teachers; }

    public void addTeacher(Teacher teacher) { teachers.add(teacher); }

    public boolean updateTeacher(String id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getID().equals(id)) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getID().equals(id)) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }
}