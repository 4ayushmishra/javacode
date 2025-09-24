package edu.ccrm.service;

import edu.ccrm.domain.Person;
import edu.ccrm.domain.Searchable;
import edu.ccrm.domain.Student;
import edu.ccrm.util.MaxCreditLimitExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentService implements Searchable<Student> {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students); // Defensive copy
    }

    public void updateStudent(String id, String newEmail) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setEmail(newEmail); // Assuming setter, but encapsulated
                return;
            }
        }
    }

    public void deactivateStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setStatus("DEACTIVATED");
                return;
            }
        }
    }

    public void enroll(Student student, Enrollment enrollment) throws MaxCreditLimitExceededException {
        int currentCredits = student.getEnrolledCourses().stream().mapToInt(e -> e.getCourse().getCredits()).sum();
        if (currentCredits + enrollment.getCourse().getCredits() > 20) { // Business rule: max 20 credits/semester
            throw new MaxCreditLimitExceededException("Exceeded max credits for semester");
        }
        student.getEnrolledCourses().add(enrollment);
    }

    public void unenroll(Student student, Course course) {
        student.getEnrolledCourses().removeIf(e -> e.getCourse().equals(course));
    }

    public double computeGPA(Student student) {
        List<Enrollment> enrollments = student.getEnrolledCourses();
        if (enrollments.isEmpty()) return 0.0;
        double totalPoints = enrollments.stream().mapToDouble(e -> e.getGrade().getGradePoints() * e.getCourse().getCredits()).sum();
        int totalCredits = enrollments.stream().mapToInt(e -> e.getCourse().getCredits()).sum();
        return totalPoints / totalCredits;
    }

    public void printTranscript(Student student) {
        Student.TranscriptBuilder builder = student.new TranscriptBuilder(); // Inner class usage
        for (Enrollment e : student.getEnrolledCourses()) {
            builder.addEnrollment(e);
        }
        System.out.println(builder.build());
        System.out.println("GPA: " + computeGPA(student));
    }

    @Override
    public List<Student> search(Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    // Polymorphism: Treat as Person
    public void printRole(Person person) {
        System.out.println(person.getRole()); // Virtual invocation
    }

    // Upcast/Downcast example
    public void processPerson(Person person) {
        if (person instanceof Student) { // instanceof check (necessary for type-specific ops)
            Student student = (Student) person; // Downcast
            System.out.println(student.getRegNo());
        }
    }
}