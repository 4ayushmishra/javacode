package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Searchable;
import edu.ccrm.domain.Semester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseService implements Searchable<Course> {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    public void updateCourse(CourseCode code, String newTitle) {
        for (Course c : courses) {
            if (c.getCode().equals(code)) {
                c.setTitle(newTitle); // Assuming setter, but private
                return;
            }
        }
    }

    public void deactivateCourse(CourseCode code) {
        // Similar to update, set status if added
    }

    public void assignInstructor(Course course, Instructor instructor) {
        course.setInstructor(instructor);
    }

    // Search & filter using Streams
    public List<Course> filterByInstructor(Instructor instructor) {
        return courses.stream().filter(c -> c.getInstructor().equals(instructor)).collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String department) {
        return courses.stream().filter(c -> c.getDepartment().equals(department)).collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester semester) {
        return courses.stream().filter(c -> c.getSemester() == semester).collect(Collectors.toList());
    }

    @Override
    public List<Course> search(Predicate<Course> predicate) {
        return courses.stream().filter(predicate).collect(Collectors.toList());
    }

    // Arrays demo: Sort course codes
    public void sortCourseCodes() {
        String[] codes = courses.stream().map(c -> c.getCode().getCode()).toArray(String[]::new);
        Arrays.sort(codes); // Arrays utility
        System.out.println("Sorted codes: " + Arrays.toString(codes));
        // Binary search
        int index = Arrays.binarySearch(codes, "CS101");
        System.out.println("Index of CS101: " + index);
    }
}