package src;

import java.io.Serializable;

/**
 * Represents a university course.
 * Implements Serializable for file storage.
 */
public class Course implements Serializable, Displayable {
    private String courseId;
    private String courseName;
    private int credits;
    private int capacity;
    private int enrolledCount;

    public Course(String courseId, String courseName, int credits, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public void incrementEnrollment() throws CourseFullException {
        if (enrolledCount >= capacity) {
            throw new CourseFullException("Course " + courseName + " is full!");
        }
        enrolledCount++;
    }

    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    
    // --- NEW METHODS ADDED HERE ---
    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolledCount; }
    // ------------------------------

    @Override
    public void displayDetails() {
        System.out.println("ID: " + courseId + " | Name: " + courseName + 
                           " | Credits: " + credits + " | Seats: " + enrolledCount + "/" + capacity);
    }
    
    @Override
    public String toString() {
        return courseName + " (" + courseId + ")";
    }
}