package edu.ccrm.util;

import java.io.IOException;

public class DuplicateEnrollmentException extends IOException {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}