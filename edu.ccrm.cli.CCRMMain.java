package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import edu.ccrm.util.DuplicateEnrollmentException;
import edu.ccrm.util.MaxCreditLimitExceededException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class CCRMMain {
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static TranscriptService transcriptService = new ConsoleTranscriptService(studentService);
    private static ImportExportService ioService = new ImportExportService();
    private static BackupService backupService = new BackupService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance(); // Singleton
        System.out.println("Data folder: " + config.getDataFolder());

        // Anonymous inner class example
        Runnable greeting = new Runnable() {
            @Override
            public void run() {
                System.out.println("Welcome to CCRM!");
            }
        };
        greeting.run();

        // Platform note from README
        System.out.println("Java SE: Standard Edition for desktop/console apps like this.");
        System.out.println("Java ME: Micro Edition for embedded devices.");
        System.out.println("Java EE: Enterprise Edition for server-side apps (now Jakarta EE).");

        // Operators demo (in comments)
        // Arithmetic: int a = 5 + 3; Relational: a > 2; Logical: (a > 0) && true; Bitwise: a & 1;
        // Precedence: 2 + 3 * 4 = 14 (multiplication first)

        // Strings demo
        String example = "Hello,World";
        String[] parts = example.split(",");
        System.out.println(String.join(" ", parts)); // Hello World
        System.out.println(example.substring(0, 5)); // Hello
        System.out.println(example.equals("hello,world")); // false
        System.out.println(example.compareTo("Hello,World")); // 0

        // Menu loop
        boolean running = true;
        outer: while (running) { // Labeled loop for jump
            System.out.println("Menu:");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grades");
            System.out.println("4. Import/Export");
            System.out.println("5. Backup");
            System.out.println("6. Reports");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) { // Enhanced switch possible in Java 21, but classic for demo
                case 1 -> manageStudents();
                case 2 -> manageCourses();
                case 3 -> manageEnrollments();
                case 4 -> manageIO();
                case 5 -> {
                    try {
                        backupService.backup();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 6 -> reports();
                case 7 -> {
                    running = false;
                    continue outer; // Labeled continue (demo)
                }
                default -> System.out.println("Invalid choice");
            }
        }

        // Do-while demo
        int i = 0;
        do {
            System.out.println("Do-while: " + i);
            i++;
        } while (i < 3);

        // For loop with break/continue
        for (int j = 0; j < 10; j++) {
            if (j % 2 == 0) continue;
            if (j > 5) break;
            System.out.println("For: " + j);
        }

        // Enhanced for
        for (Student s : studentService.listStudents()) {
            System.out.println(s);
        }
    }

    private static void manageStudents() {
        // Add/list/update/deactivate logic using scanner
        // Example: Student s = new Student("1", "John Doe", "john@example.com", "REG001", "ACTIVE", LocalDate.now());
        // studentService.addStudent(s);
    }

    private static void manageCourses() {
        // Similar
        // Course c = new Course.Builder().code(new CourseCode("CS101")).title("Intro to CS").credits(3).semester(Semester.FALL).department("CS").build();
        // courseService.addCourse(c);
    }

    private static void manageEnrollments() {
        // Enroll/unenroll, record marks
        try {
            // Example enrollment
            // Enrollment e = new Enrollment(student, course);
            // enrollmentService.checkDuplicate(student, e);
            // studentService.enroll(student, e);
        } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Enrollment processed");
        }
    }

    private static void manageIO() {
        try {
            ioService.importStudents(studentService, Paths.get("test-data/students.csv"));
            // Export similar
        } catch (IOException e) {
            // Multi-catch
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reports() {
        ioService.gpaDistribution(studentService);
        courseService.sortCourseCodes();
        // Lambda demo
        Validators.sortList(courseService.listCourses(), (c1, c2) -> c1.getTitle().compareTo(c2.getTitle()));
    }

    // Diamond problem demo: Assume another interface with same default, override in impl
    // e.g., class Impl implements Searchable<T>, AnotherInterface { @Override defaultMethod() {} }
}