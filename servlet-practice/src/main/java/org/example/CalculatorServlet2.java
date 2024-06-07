package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate2")
public class CalculatorServlet2 extends GenericServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet2.class);
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(servletRequest.getParameter("operand1"));
        String operator = servletRequest.getParameter("operator");
        int operand2 = Integer.parseInt(servletRequest.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));

        PrintWriter writer = servletResponse.getWriter();
        writer.println(result);
    }
}
