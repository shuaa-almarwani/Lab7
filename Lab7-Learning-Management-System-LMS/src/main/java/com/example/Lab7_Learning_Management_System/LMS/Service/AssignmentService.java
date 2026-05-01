package com.example.Lab7_Learning_Management_System.LMS.Service;

import com.example.Lab7_Learning_Management_System.LMS.Model.Assignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {
    ArrayList<Assignment> assignments = new ArrayList<>();

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public boolean updateAssignment(String id, Assignment assignment) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getID().equals(id)) {
                assignments.set(i, assignment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAssignment(String id) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getID().equals(id)) {
                assignments.remove(i);
                return true;
            }
        }
        return false;
    }

    public int checkGrade(String id, double studentScore) {
        for (Assignment a : assignments) {
            if (a.getID().equals(id)) {
                if (studentScore > a.getTotalMarks()) {
                    return 2;
                }
                return 3;
            }
        }
        return 1;
    }

    public ArrayList<Assignment> getHardAssignments() {
        ArrayList<Assignment> hardAssignments = new ArrayList<>();
        for (Assignment a : assignments) {
            if (a.getTotalMarks() > 50) {
                hardAssignments.add(a);
            }
        }
        return hardAssignments;
    }
    public boolean extendDeadline(String id, int extraDays) {
        for (Assignment a : assignments) {
            if (a.getID().equals(id)) {
                a.setDeadlineInDays(a.getDeadlineInDays() + extraDays);
                return true;
            }
        }
        return false;
    }
}
