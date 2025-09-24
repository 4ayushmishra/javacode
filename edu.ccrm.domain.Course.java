package edu.ccrm.domain;

public class Course {
    private CourseCode code; // Immutable value object
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;

    // Private constructor for Builder
    private Course(CourseCode code, String title, int credits, Instructor instructor, Semester semester, String department) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.semester = semester;
        this.department = department;
    }

    public CourseCode getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Course{code=" + code + ", title='" + title + "', credits=" + credits + ", instructor=" + instructor + ", semester=" + semester + ", department='" + department + "'}";
    }

    // Static nested class for Builder pattern
    public static class Builder {
        private CourseCode code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;
        private String department;

        public Builder code(CourseCode code) {
            this.code = code;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Course build() {
            assert code != null : "Code cannot be null";
            assert credits > 0 && credits <= 5 : "Credits must be between 1 and 5";
            return new Course(code, title, credits, instructor, semester, department);
        }
    }
}