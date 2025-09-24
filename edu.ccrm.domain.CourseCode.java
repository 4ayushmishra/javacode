package edu.ccrm.domain;

// Immutable class with final fields and defensive copying
public final class CourseCode {
    private final String code;

    public CourseCode(String code) {
        this.code = code; // Defensive copy not needed for String (immutable)
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}