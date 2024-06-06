package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    //원래는 requestLine 이외에도 헤더, body 등이 있다.
    private final RequestLine requestLine; // requestLine 파싱 객체

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine());
        //첫번째 라인이 request line
    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest(); // GET 메서드가 맞는지 RequestLine 객체에서 검사
    }

    public boolean matchPath(String path) {
        return requestLine.matchPath(path); // 파라미터로 넘긴 패스와 requestPath와 매칭
    }

    public QueryStrings getQueryStrings() {
        return requestLine.getQueryStrings(); // key-value 형식으로 담긴 쿼리스트링 리스트 리턴
    }

}
