package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    //어플리케이션이 호출
    public void start() throws IOException {
        //서버 해당 포트로 열기
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            // 클라이언트 기다리기
            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected");

                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */

                // 라인 바이 라인으로 읽어들이기 위해서 구현
                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

                    //HTTP Request 중 맨 윗 라인을 HttpRequest 생성자로 넘김 (메서드, 패스, 쿼리스트링 정보가 담겨있다.)
                    HttpRequest httpRequest = new HttpRequest(br);

                    //GET 메서드이고, /calculator 패스를 가졌다면 로직 수행
                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculator")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings(); // 쿼리스트링 리턴받음

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2)); // 계산 수행
                        byte[] body = String.valueOf(result).getBytes(); // 결과 바이트로 변환

                        HttpResponse response = new HttpResponse(dos); // 응답 보낼 경로 설정 및 응답 메세지 설정
                        response.response200Header("application/json", body.length); // 응답 형식 설정
                        response.responseBody(body); // 연산 결과 바디에 추가
                    }
                }
            }
        }
    }
}
