package org.example;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 + operand2;
        }
    }, SUBTRACTION("-") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 - operand2;
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 * operand2;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 / operand2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public static int calculation(int operand1, String operator, int operand2) {
        ArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
        return arithmeticOperator.arithmeticCalculator(operand1, operand2);
    }

    public abstract int arithmeticCalculator(final int operand1, final int operand2);
}
