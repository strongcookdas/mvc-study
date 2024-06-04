package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

                    String line;
                    while (!Objects.equals(line = br.readLine(), "")) {
                        System.out.println(line);
                    }
                }
            }
        }
    }
}
