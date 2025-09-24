package edu.ccrm.domain;

public enum Semester {
    SPRING("Spring Semester"),
    SUMMER("Summer Semester"),
    FALL("Fall Semester");

    private final String description;

    Semester(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}