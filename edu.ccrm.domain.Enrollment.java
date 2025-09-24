package edu.ccrm.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private Student student;
    private Course course;
    private double marks;
    private Grade grade;
    private LocalDateTime enrollmentTime;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentTime = LocalDateTime.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDateTime getEnrollmentTime() {
        return enrollmentTime;
    }

    @Override
    public String toString() {
        return "Enrollment{student=" + student.getFullName() + ", course=" + course.getTitle() + ", marks=" + marks + ", grade=" + grade + ", time=" + enrollmentTime + "}";
    }
}