package org.example;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;
    //일급 콜렉션
    // 리스트 형태로 된 정보를 인스턴스 변수로 가지는 클래스이다.
    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculate() {
        double multipliedCreditAndCourseGrads = courses.multiplyCreditAndCourseGrade();
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();
        return multipliedCreditAndCourseGrads / totalCompletedCredit;
    }
}
