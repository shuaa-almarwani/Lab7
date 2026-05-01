package com.example.Lab7_Learning_Management_System.LMS.Service;

import com.example.Lab7_Learning_Management_System.LMS.Model.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class ProjectService {
    ArrayList<Project> projects = new ArrayList<>();

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public boolean updateProject(String id, Project project) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getID().equals(id)) {
                projects.set(i, project);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProject(String id) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getID().equals(id)) {
                projects.remove(i);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Project> searchByTitle(String title) {
        ArrayList<Project> found = new ArrayList<>();
        for (Project p : projects) {
            if (p.getTitle().contains(title)) {
                found.add(p);
            }
        }
        return found;
    }
    public ArrayList<Project> getProjectsByStatus(String status) {
        ArrayList<Project> filteredProjects = new ArrayList<>();
        for (Project p : projects) {
            if (p.getStatus().equalsIgnoreCase(status)) {
                filteredProjects.add(p);
            }
        }
        return filteredProjects;
    }

    public int updateStatus(String id, String newStatus) {
        for (Project p : projects) {
            if (p.getID().equals(id)) {
                if (!(newStatus.equalsIgnoreCase("Not Started") ||
                        newStatus.equalsIgnoreCase("In Progress") ||
                        newStatus.equalsIgnoreCase("Completed"))) {
                    return 2;
                }

                String current = p.getStatus();

                if (current.equalsIgnoreCase("Not Started") && newStatus.equalsIgnoreCase("Completed")) {
                    return 4;
                }

                if (current.equalsIgnoreCase("Completed") && newStatus.equalsIgnoreCase("Not Started")) {
                    return 5;
                }

                if (current.equalsIgnoreCase("In Progress") && newStatus.equalsIgnoreCase("Not Started")) {
                    return 6;
                }

                p.setStatus(newStatus);
                return 1;
            }
        }
        return 0;
    }
}