package org.example.calculator.domain;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public int calculate(final int operand1, final int operand2) {
            return operand1 + operand2;
        }
    },
    SUBTRACTION("-") {
        @Override
        public int calculate(final int operand1, final int operand2) {
            return operand1 - operand2;
        }
    },
    MULTIPLICATION("*") {
        @Override
        public int calculate(final int operand1, final int operand2) {
            return operand1 * operand2;
        }
    }, DIVISION("/") {
        @Override
        public int calculate(final int operand1, final int operand2) {
            if (operand2 == 0) {
                throw new IllegalArgumentException("It cannot be divided by 0.");
            }
            return operand1 / operand2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int calculate(final int operand1, final int operand2);

    public static int calculate(final int operand1, final String operator, final int operand2) {
        ArithmeticOperator selectedArithmeticOperator = Arrays.stream(ArithmeticOperator.values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("This is not a correct arithmetic operation."));

        return selectedArithmeticOperator.calculate(operand1, operand2);
    }
}
