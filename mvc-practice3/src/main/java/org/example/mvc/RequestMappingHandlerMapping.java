package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping{
    // 특정 경로로 요청이 들어왔을 때 해당 경로의 컨트롤러로 요청 처리하도록 설정하는 클래스
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
//        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        //해당 경로로 포워드
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }
}
