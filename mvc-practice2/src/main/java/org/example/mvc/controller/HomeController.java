package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home.jsp"; //해당 컨트롤러가 호출되면 home 화면을 반환
    }
}
