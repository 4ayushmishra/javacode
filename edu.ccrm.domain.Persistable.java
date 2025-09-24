package edu.ccrm.domain;

import java.io.IOException;

public interface Persistable {
    void save() throws IOException;
    default void load() throws IOException {
        // Default method
        System.out.println("Default load implementation");
    }
}