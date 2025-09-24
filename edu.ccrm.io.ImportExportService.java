package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.CourseCode;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class ImportExportService {
    private Path dataPath = AppConfig.getInstance().getDataFolder();

    // Import students from CSV using Streams
    public void importStudents(StudentService service, Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            lines.skip(1) // Skip header
                 .map(line -> line.split(","))
                 .forEach(parts -> {
                     Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4], LocalDate.parse(parts[5]));
                     service.addStudent(student);
                 });
        }
    }

    // Similar for courses
    public void importCourses(CourseService service, Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            lines.skip(1)
                 .map(line -> line.split(","))
                 .forEach(parts -> {
                     Course course = new Course.Builder()
                             .code(new CourseCode(parts[0]))
                             .title(parts[1])
                             .credits(Integer.parseInt(parts[2]))
                             // Instructor skipped for simplicity
                             .semester(Semester.valueOf(parts[3]))
                             .department(parts[4])
                             .build();
                     service.addCourse(course);
                 });
        }
    }

    // Export students to CSV
    public void exportStudents(List<Student> students, Path file) throws IOException {
        List<String> lines = students.stream()
                .map(s -> String.join(",", s.getId(), s.getFullName(), s.getEmail(), s.getRegNo(), s.getStatus(), s.getEnrollmentDate().toString()))
                .toList();
        Files.write(file, lines);
    }

    // Similar for courses, enrollments

    // Stream pipeline for report (e.g., GPA distribution)
    public void gpaDistribution(StudentService service) {
        service.listStudents().stream()
                .collect(Collectors.groupingBy(s -> {
                    double gpa = service.computeGPA(s);
                    return gpa >= 8.0 ? "High" : (gpa >= 6.0 ? "Medium" : "Low");
                }))
                .forEach((key, value) -> System.out.println(key + ": " + value.size()));
    }
}