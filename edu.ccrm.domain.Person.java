package edu.ccrm.domain;

public abstract class Person {
    private String id;
    private String fullName;
    private String email;

    public Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // Abstract method for abstraction
    public abstract String getRole();

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{id='" + id + "', fullName='" + fullName + "', email='" + email + "'}";
    }
}