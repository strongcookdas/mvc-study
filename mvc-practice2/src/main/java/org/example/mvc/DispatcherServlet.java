package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")// 어떠한 경로로 요청이 들어와도 해당 서블릿으로 처리
public class DispatcherServlet extends HttpServlet { //서블릿이 되고 톰캣이 실행할 수 있다.
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping rmhm;

    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init(); // 초기화
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatcherServletService] stared");

        Controller handler = rmhm.findHandler(req.getRequestURI());

        //핸들러에게 작업을 위임
        try {
            String viewName = handler.handleRequest(req, resp);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
        }
    }
}
