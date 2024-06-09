package org.example.mvc;

import org.example.mvc.controller.AnnotationHandlerAdapter;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/")// 어떠한 경로로 요청이 들어와도 해당 서블릿으로 처리
public class DispatcherServlet extends HttpServlet { //서블릿이 되고 톰캣이 실행할 수 있다.
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;
    private List<ViewResolver> viewResolvers;
    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        rmhm.init(); // 초기화

        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("org.example");
        ahm.initialize();

        handlerMappings = List.of(rmhm, ahm);
        handlerAdapters = List.of(new SingleControllerAdapter(), new AnnotationHandlerAdapter());
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatcherServletService] stared");
        String requestURI = req.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(req.getMethod());


        //핸들러에게 작업을 위임
        try {
            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                    .map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler for [" + requestMethod + ", " + requestURI + "]"));

            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            ModelAndView modelAndView = handlerAdapter.handle(req, resp, handler);

            // redirect와 forward가 되도록 변경
            // 핸들러 어댑터를 통해서 핸들러를 실행하도록 변경
            // 뷰 리졸버를 통해서 리다이렉트 뷰인지 jsp 뷰인지 판단해야한다.
            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), req, resp);
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
        }
    }
}
