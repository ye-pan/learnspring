package com.yp.spring.aop.foo;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;


/**
 * 方法正常执行完成后调用
 */
public class TaskExecutionAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

    }
}
