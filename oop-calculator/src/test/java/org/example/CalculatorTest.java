package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * • 요구사항
 * • 간단한 사칙연산을 할 수 있다.
 * • 양수로만 계산할 수 있다.
 * • 나눗셈에서  0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * • MVC패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {
    @Test
    @DisplayName("덧셈 연산을 수행한다.")
    void additionTest() {
        int result = Calculator.calculator(1, "+", 2);
        assertThat(result).isEqualTo(3);
    }
    @Test
    @DisplayName("뺄셈 연산을 수행한다.")
    void subtractionTest() {
        int result = Calculator.calculator(1, "-", 2);
        assertThat(result).isEqualTo(-1);
    }

}
