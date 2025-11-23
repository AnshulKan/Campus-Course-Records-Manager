package src;

/**
 * Custom exception thrown when a student tries to enroll in a full course.
 */
public class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}