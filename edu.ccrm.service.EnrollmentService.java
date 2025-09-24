package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.util.DuplicateEnrollmentException;

import java.util.List;

public class EnrollmentService {
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void recordMarks(Enrollment enrollment, double marks) {
        enrollment.setMarks(marks);
    }

    // Check for duplicate enrollment
    public void checkDuplicate(Student student, Enrollment enrollment) throws DuplicateEnrollmentException {
        if (student.getEnrolledCourses().stream().anyMatch(e -> e.getCourse().equals(enrollment.getCourse()))) {
            throw new DuplicateEnrollmentException("Student already enrolled in this course");
        }
    }
}