package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    private Object createInstance(Class<?> clazz) {
        // 생성자
        Constructor<?> constructor = findConstructor(clazz);

        // 파라미터
        List<Object> parameters = new ArrayList<>();
        for(Class<?> typeClass : constructor.getParameterTypes()){
            // 파라미터 클래스 타입에 해당하는 인스턴스를 가져오는 메서드
            parameters.add(getParameterByClass(typeClass));
        }

        //인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    private Constructor<?> findConstructor(Class<?> clazz) {
        //Inject 어노테이션이 붙은 생성자만 가져온다
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if(Objects.nonNull(constructor)){
            return constructor;
        }
        return clazz.getConstructors()[0];
    }
    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);
        if(Objects.nonNull(instanceBean)){
            return instanceBean;
        }
        return createInstance(typeClass);
    }

    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
