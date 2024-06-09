package org.example;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws Exception {
        //톰캣 8080 포트로 설정
        //실행했을 때 webapps를 루트로 바라보겠다.
        //빌드 파일이 자동으로 webapps 아래에 들어온다.
        //톰캣의 규약이다. 패스설정 잘 해야한다.
        String webappDirLocation = "webapps/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }}