import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private List<String> enrolledStudents;
    private String prerequisite;

    public Course(String name, int capacity, String prerequisite) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisite = prerequisite;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void enroll(String studentName) throws CourseFullException {
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("Course is full.");
        }
        enrolledStudents.add(studentName);
        System.out.println("Successfully enrolled in " + name);
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Course advancedJava = new Course("Advanced Java", 2, "Core Java");
        
        System.out.print("Enroll in Course: ");
        String courseName = scanner.nextLine();
        
        if (courseName.equalsIgnoreCase(advancedJava.getName())) {
            System.out.print("Prerequisite: ");
            String prerequisite = scanner.nextLine();
            
            if (!prerequisite.equalsIgnoreCase(advancedJava.getPrerequisite())) {
                System.out.println("Error: PrerequisiteNotMetException - Complete " + advancedJava.getPrerequisite() + " before enrolling in " + advancedJava.getName() + ".");
            } else {
                try {
                    advancedJava.enroll("Student");
                } catch (CourseFullException e) {
                    System.out.println("Error: CourseFullException - " + e.getMessage());
                }
            }
        } else {
            System.out.println("Error: Course not found.");
        }
        
        scanner.close();
    }
}