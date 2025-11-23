package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class managing the business logic.
 */
public class RecordManager {
    private List<User> users;
    private Map<String, Course> courses;

    public RecordManager() {
        users = new ArrayList<>();
        courses = new HashMap<>();
        if (FileHandler.loadData(users, courses)) {
            System.out.println("Welcome back! Data loaded.");
        } else {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    public void saveData() {
        FileHandler.saveData(users, courses);
    }

    public User login(String id, String password) {
        for (User u : users) {
            if (u.getId().equals(id) && u.validateLogin(password)) {
                return u;
            }
        }
        return null;
    }

    public void registerUser(User u) {
        users.add(u);
        saveData();
    }

    public void addCourse(Course c) {
        courses.put(c.getCourseId(), c);
        saveData();
    }

    public Map<String, Course> getCourses() {
        return courses;
    }
    
    public Student findStudentById(String id) {
        for(User u : users) {
            if(u instanceof Student && u.getId().equals(id)) {
                return (Student) u;
            }
        }
        return null;
    }

    // --- NEW METHOD ADDED HERE ---
    public List<Student> getStudentsEnrolledIn(String courseId) {
        List<Student> enrolled = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Student) {
                Student s = (Student) u;
                // Check if the student has a record (grade) for this course ID
                if (s.getGrades().containsKey(courseId)) {
                    enrolled.add(s);
                }
            }
        }
        return enrolled;
    }
    // -----------------------------

    public void enrollStudent(Student s, String courseId) throws CourseFullException {
        Course c = courses.get(courseId);
        if (c != null) {
            c.incrementEnrollment(); // May throw CourseFullException
            s.enrollInCourse(courseId);
            saveData();
            System.out.println("Successfully enrolled in " + c.getCourseName());
        } else {
            System.out.println("Course not found.");
        }
    }
}