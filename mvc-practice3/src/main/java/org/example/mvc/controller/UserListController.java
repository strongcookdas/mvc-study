package org.example.mvc.controller;

import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //list.jsp를 전달할 때 list.jsp로 이동을 시킬 때 users라는 정보도 함께 전달해 줘야 함
        request.setAttribute("users", UserRepository.findAll());
        return "/user/list";
    }
}
