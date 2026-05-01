package com.example.Lab7_Learning_Management_System.LMS.Service;

import com.example.Lab7_Learning_Management_System.LMS.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

        ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) { students.add(student); }


    public boolean updateStudent(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }


        public String checkGraduation(String id) {
            for (Student s : students) {
                if (s.getID().equals(id)) {
                    return (s.getLevel() == 8) ? "Expected to Graduate" : "Regular Student";
                }
            }
            return null;
        }
    }

