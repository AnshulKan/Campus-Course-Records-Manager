package src;

import java.util.HashMap;
import java.util.Map;

public class Student extends User {
    // Map Stores CourseID -> Grade (Double)
    private Map<String, Double> grades;
    
    public Student(String id, String name, String password) {
        super(id, name, password);
        this.grades = new HashMap<>();
    }

    public void enrollInCourse(String courseId) {
        // Logic handled in RecordManager, this just tracks the record locally
        if(!grades.containsKey(courseId)) {
            grades.put(courseId, -1.0); // -1 indicates not graded yet
        }
    }

    public void receiveGrade(String courseId, double grade) {
        if (grades.containsKey(courseId)) {
            grades.put(courseId, grade);
        }
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- Student Menu (" + name + ") ---");
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. View Transcript/Grades");
        System.out.println("4. Logout");
    }

    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + id + " | Name: " + name);
    }
}