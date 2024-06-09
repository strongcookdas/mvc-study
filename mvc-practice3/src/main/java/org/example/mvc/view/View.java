package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
    // jsp 뷰일수도 있고 리다이렉트 뷰일수도 있기 때문에 다형성을 위해 인터페이스로 선언
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
