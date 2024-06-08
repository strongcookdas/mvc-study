package org.example;

import org.example.anotation.Controller;
import org.example.anotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans : [{}]", beans);
    }

    @Test
    void showClass() { // 클래스 정보를 이렇게 확인할 수 있다.
        Class<User> userClass = User.class;
        logger.debug(userClass.getName());
        logger.debug("User all declared fields : [{}]", Arrays.stream(userClass.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared construct : [{}]", Arrays.stream(userClass.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared construct : [{}]", Arrays.stream(userClass.getDeclaredMethods()).collect(Collectors.toList()));

    }

    @Test
    void load() throws ClassNotFoundException {
        // 힙 영역에 로드되어 있는 객체를 가져오는 방법
        Class<User> userClass = User.class;

        //두번째 방법
        User user = new User("userId", "user");
        Class<? extends User> userClass2 = user.getClass();

        //세번재 방법
        Class<?> userClass3 = Class.forName("org.example.model.User");

        logger.debug("class1: [{}]", userClass);
        logger.debug("class2: [{}]", userClass2);
        logger.debug("class3: [{}]", userClass3);

        // 같은 객체이다.
        assertThat(userClass == userClass2).isTrue();
        assertThat(userClass2 == userClass3).isTrue();
        assertThat(userClass3 == userClass).isTrue();

    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotaions) {
        Reflections reflections = new Reflections("org.example"); // 해당 패키지 밑으로 모두 리플렉션 사용
        Set<Class<?>> beans = new HashSet<>();
        annotaions.forEach(annotaion -> beans.addAll(reflections.getTypesAnnotatedWith(annotaion))); //해당 어노테이션이 있는 클래스를 찾아 set에 추가
        return beans;
    }
}
