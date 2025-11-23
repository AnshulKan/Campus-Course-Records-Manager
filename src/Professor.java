package src;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    private List<String> coursesTaught;

    public Professor(String id, String name, String password) {
        super(id, name, password);
        this.coursesTaught = new ArrayList<>();
    }

    public void assignCourse(String courseId) {
        coursesTaught.add(courseId);
    }

    public List<String> getCoursesTaught() {
        return coursesTaught;
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- Professor Menu (" + name + ") ---");
        System.out.println("1. Create New Course");
        System.out.println("2. View My Courses");
        System.out.println("3. View Enrolled Students"); // This was missing
        System.out.println("4. Grade Student");
        System.out.println("5. Logout");
    }

    @Override
    public void displayDetails() {
        System.out.println("Professor ID: " + id + " | Name: " + name);
    }
}