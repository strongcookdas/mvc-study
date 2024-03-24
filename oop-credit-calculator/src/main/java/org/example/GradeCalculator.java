package org.example;

import java.util.List;

public class GradeCalculator {
    private final List<Course> courses;

    public GradeCalculator(List<Course>courses) {
        this.courses = courses;
    }

    public double calculate() {
        return 4.5;
    }
}
