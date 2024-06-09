package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    // 특정 경로로 요청이 들어왔을 때 해당 경로의 컨트롤러로 요청 처리하도록 설정하는 클래스
    private Map<String, Controller> mappings = new HashMap<>();
    void init(){
        mappings.put("/",new HomeController());
    }

    public Controller findHandler(String uriPath){
        return mappings.get(uriPath);
    }
}
