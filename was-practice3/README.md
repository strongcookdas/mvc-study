# 요청이 들어올 때마다 스레드 생성
## 메인 스레드에서 처리하면 생기는 문제점

- 요청 처리가 오래 걸리면 그 뒤 요청은 기다려야 한다.

## 요청이 들어올 때마다 스레드 생성

```java
public class ClientRequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        /**
         * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성하여 사용자 요청을 처리
         */
        logger.info("[ClientRequestHandler] new client {} started.", Thread.currentThread().getName());
        try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(out);
            HttpRequest httpRequest = new HttpRequest(br);
            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculator")) {
                QueryStrings queryStrings = httpRequest.getQueryStrings();

                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2)); // 계산 수행
                byte[] body = String.valueOf(result).getBytes();

                HttpResponse response = new HttpResponse(dos);
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
```

- `Runable` 인터페이스를 상속받아 쓰레드로 처리할 로직을 start 메서드 내부에서 구현

```java
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

                //요청들어올 때마다 스레드 생성
                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        }
    }
}
```

- 요청이 들어올 때마다 스레드 생성

## 요청이 들어올 때마다 스레드 생성하면 생기는 문제점

- 스레드를 생성하면 독립적인 스택 메모리 공간을 할당
- 메모리 할당이 계속되면 성능 저하
- 동시 접속자 수가 발생하면 스레드 많아짐
- cpu 컨텍스트 스위칭이 많아지고 cpu, 메모리 사용량이 많아짐
- 최악의 경우 서버 다운

## 해결 방법

- 스레드 풀 생성하여 고정적인 스레드 수로 요청 처리