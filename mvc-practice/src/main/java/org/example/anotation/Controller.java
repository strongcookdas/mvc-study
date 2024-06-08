package org.example.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 클래스나 인터페이스나 ENUM 선언에 붙일 수 있는 어노테이션이라고 선언
@Retention(RetentionPolicy.RUNTIME)//유지 기간은 RunTime 기간까지
public @interface Controller {
}
