package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.*;

/**
 * • 요구사항
 * • 간단한 사칙연산을 할 수 있다.
 * • 양수로만 계산할 수 있다.
 * • 나눗셈에서  0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * • MVC패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    @DisplayName("덧셈 연산을 수행한다.")
    void calculatorTest(int operand1, String operator, int operand2, int result) {
        int calculatorResult = Calculator.calculator(operand1, operator, operand2);
        assertThat(calculatorResult).isEqualTo(result);
    }

    static private Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(1, "*", 2, 2),
                arguments(6, "/", 2, 3)
        );
    }

}
