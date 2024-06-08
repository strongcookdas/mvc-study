package org.example.controller;

import org.example.anotation.Controller;
import org.example.anotation.RequestMapping;
import org.example.anotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response){
        return "home";
    }
}
