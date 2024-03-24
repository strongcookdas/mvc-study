package org.example;

import java.util.List;

public class GradeCalculator {
    private final List<Course> courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = courses;
    }

    /*
• 요구사항
• 평균학점 계산 방법 = (학점수×교과목 평점)의 합계/수강신청 총학점 수
• MVC패턴(Model-View-Controller) 기반으로 구현한다.
• 일급 컬렉션 사용
 */
    public double calculate() {
        double multipliedCreditAndCourseGrads = 0;
        for (Course course : courses) {
            multipliedCreditAndCourseGrads += course.getCredit() * course.getGradeToNumber();
        }
        int totalCompletedCredit = courses.stream()
                .mapToInt(Course::getCredit)
                .sum();

        return multipliedCreditAndCourseGrads / totalCompletedCredit;
    }
}
