package org.example;

import java.util.Objects;

public class RequestLine {
    private final String method;
    private final String urlPath;
    private QueryStrings queryString;
    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryString = new QueryStrings(queryString);
    }

    /**
     * GET /calculator?operand1=11&operator=*&operand2=55 HTTP/1.1
     */
    public RequestLine(String requestLine) {
        //method path http 버전 분리
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        //path와 queryString 분리
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];
        if (urlPathTokens.length == 2) {
            this.queryString = new QueryStrings(urlPathTokens[1]);
        }
    }
    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String path) {
        return urlPath.equals(path);
    }

    public QueryStrings getQueryStrings() {
        return this.queryString;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryString, that.queryString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryString);
    }
}
