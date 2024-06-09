package org.example.mvc.controller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //user 추가
        UserRepository.save(new User(request.getParameter("userId"),request.getParameter("name")));
        // 클라이언트에게 /users로 가라고 응답 내리면 클라이언트는 다시 /users로 요청을 보낸다.
        return "redirect:/users";
    }
}
