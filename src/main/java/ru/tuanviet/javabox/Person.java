package ru.tuanviet.javabox;

public class Person {

    private final String firstName;
    private final String secondName;

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }
}
