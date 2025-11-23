package src;

import java.util.Map;

/**
 * Helper class to generate reports.
 */
public class TranscriptGenerator {
    
    public static void printTranscript(Student student, Map<String, Course> allCourses) {
        System.out.println("\n========================================");
        System.out.println("OFFICIAL TRANSCRIPT: " + student.getName());
        System.out.println("========================================");
        
        double totalPoints = 0;
        int totalCredits = 0;
        
        Map<String, Double> grades = student.getGrades();
        
        if (grades.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            String courseId = entry.getKey();
            Double grade = entry.getValue();
            Course c = allCourses.get(courseId);
            
            if (c != null) {
                String gradeStr = (grade == -1.0) ? "In Progress" : String.valueOf(grade);
                System.out.printf("Course: %-10s | Credit: %d | Grade: %s%n", 
                        c.getCourseName(), c.getCredits(), gradeStr);
                
                if (grade != -1.0) {
                    totalPoints += (grade * c.getCredits());
                    totalCredits += c.getCredits();
                }
            }
        }
        
        System.out.println("----------------------------------------");
        double gpa = (totalCredits == 0) ? 0.0 : (totalPoints / totalCredits);
        System.out.printf("Calculated GPA: %.2f%n", gpa);
        System.out.println("========================================");
    }
}