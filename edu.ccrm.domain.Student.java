package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private String status; // e.g., ACTIVE, DEACTIVATED
    private List<Enrollment> enrolledCourses = new ArrayList<>();
    private LocalDate enrollmentDate;

    public Student(String id, String fullName, String email, String regNo, String status, LocalDate enrollmentDate) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = status;
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public String getRegNo() {
        return regNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Enrollment> getEnrolledCourses() {
        return enrolledCourses;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    // Polymorphic toString override
    @Override
    public String toString() {
        return "Student{" + super.toString() + ", regNo='" + regNo + "', status='" + status + "', enrollmentDate=" + enrollmentDate + "}";
    }

    // Print profile (uses polymorphism)
    public void printProfile() {
        System.out.println(this); // Calls overridden toString
    }

    // Inner class example (non-static)
    public class TranscriptBuilder {
        // Builder pattern for transcript (nested inner class)
        private List<Enrollment> enrollments = new ArrayList<>();

        public TranscriptBuilder addEnrollment(Enrollment enrollment) {
            enrollments.add(enrollment);
            return this;
        }

        public String build() {
            StringBuilder sb = new StringBuilder("Transcript for " + fullName + ":\n");
            for (Enrollment e : enrollments) {
                sb.append(e).append("\n");
            }
            return sb.toString();
        }
    }
}