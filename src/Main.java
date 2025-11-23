package src;

import java.util.Scanner;

/**
 * Main entry point for the CCRM application.
 */
public class Main {
    private static RecordManager manager = new RecordManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== CAMPUS COURSE & RECORDS MANAGER ===");
            System.out.println("1. Login");
            System.out.println("2. Register New Account");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = -1;
            try {
                 choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1: handleLogin(); break;
                case 2: handleRegistration(); break;
                case 3: 
                    System.out.println("Saving and Exiting...");
                    manager.saveData();
                    System.exit(0);
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void handleLogin() {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        User user = manager.login(id, pass);
        if (user != null) {
            System.out.println("Login Successful!");
            userSession(user);
        } else {
            System.out.println("Invalid Credentials.");
        }
    }

    private static void handleRegistration() {
        System.out.println("Register as: 1. Student  2. Professor");
        String type = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        if (type.equals("1")) {
            manager.registerUser(new Student(id, name, pass));
            System.out.println("Student registered!");
        } else if (type.equals("2")) {
            manager.registerUser(new Professor(id, name, pass));
            System.out.println("Professor registered!");
        } else {
            System.out.println("Invalid type selected.");
        }
    }

    private static void userSession(User user) {
        boolean loggedIn = true;
        while (loggedIn) {
            user.showMenu();
            System.out.print("Select option: ");
            String input = scanner.nextLine();

            if (user instanceof Student) {
                Student s = (Student) user;
                switch (input) {
                    case "1": // View Courses
                        manager.getCourses().values().forEach(Displayable::displayDetails);
                        break;
                    case "2": // Enroll
                        System.out.print("Enter Course ID to enroll: ");
                        String cid = scanner.nextLine();
                        try {
                            manager.enrollStudent(s, cid);
                        } catch (CourseFullException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case "3": // Transcript
                        TranscriptGenerator.printTranscript(s, manager.getCourses());
                        break;
                    case "4": loggedIn = false; break;
                    default: System.out.println("Invalid option.");
                }
            } else if (user instanceof Professor) {
                Professor p = (Professor) user;
                switch (input) {
                    case "1": // Create Course
                        System.out.print("Course ID: "); String cid = scanner.nextLine();
                        System.out.print("Name: "); String cname = scanner.nextLine();
                        System.out.print("Credits: "); int creds = Integer.parseInt(scanner.nextLine());
                        System.out.print("Capacity: "); int cap = Integer.parseInt(scanner.nextLine());
                        Course c = new Course(cid, cname, creds, cap);
                        manager.addCourse(c);
                        p.assignCourse(cid);
                        System.out.println("Course created successfully.");
                        break;
                    case "2": // View My Courses (Table Format)
                        System.out.println("\n--- My Courses ---");
                        System.out.printf("%-10s | %-20s | %-10s | %-15s%n", "ID", "Name", "Credits", "Enrolled/Cap");
                        System.out.println("---------------------------------------------------------------");
                        for (String taughtId : p.getCoursesTaught()) {
                            Course taughtCourse = manager.getCourses().get(taughtId);
                            if (taughtCourse != null) {
                                System.out.printf("%-10s | %-20s | %-10d | %d/%d%n", 
                                    taughtCourse.getCourseId(), 
                                    taughtCourse.getCourseName(), 
                                    taughtCourse.getCredits(),
                                    taughtCourse.getEnrolledCount(),
                                    taughtCourse.getCapacity());
                            }
                        }
                        System.out.println("---------------------------------------------------------------");
                        break;
                    case "3": // View Enrolled Students
                        System.out.print("Enter Course ID to view students: ");
                        String viewCid = scanner.nextLine();
                        // Check if professor actually teaches this course
                        if (p.getCoursesTaught().contains(viewCid)) {
                            java.util.List<Student> enrolledStudents = manager.getStudentsEnrolledIn(viewCid);
                            if (enrolledStudents.isEmpty()) {
                                System.out.println("No students enrolled in this course yet.");
                            } else {
                                System.out.println("\n--- Students Enrolled in " + viewCid + " ---");
                                System.out.printf("%-10s | %-20s%n", "ID", "Name");
                                System.out.println("--------------------------------");
                                for (Student st : enrolledStudents) {
                                    System.out.printf("%-10s | %-20s%n", st.getId(), st.getName());
                                }
                            }
                        } else {
                            System.out.println("You do not teach this course (or it does not exist).");
                        }
                        break;
                    case "4": // Grade Student
                        System.out.print("Enter Student ID: "); String sid = scanner.nextLine();
                        Student s = manager.findStudentById(sid);
                        if (s != null) {
                            System.out.print("Enter Course ID: "); String courseId = scanner.nextLine();
                            System.out.print("Enter Grade (0.0 - 10.0): "); double grade = Double.parseDouble(scanner.nextLine());
                            s.receiveGrade(courseId, grade);
                            manager.saveData();
                            System.out.println("Grade assigned.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case "5": loggedIn = false; break;
                    default: System.out.println("Invalid option.");
                }
            }
        }
    }
}