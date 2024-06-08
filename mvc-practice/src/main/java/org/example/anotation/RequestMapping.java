package org.example.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})//메서드에도 추가 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
    RequestMethod[] method() default{};
}
