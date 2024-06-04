package org.example;

public class Person {

    private final Integer id;
    private final String first;

    private final String last;

    private final String dob;

    public Person(Integer id, String first, String last, String dob) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public String getLast() {
        return last;
    }

    public String getDob() {
        return dob;
    }

    public String getFirst() {
        return first;
    }
}
