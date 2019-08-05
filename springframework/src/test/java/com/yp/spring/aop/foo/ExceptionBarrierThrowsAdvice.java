package com.yp.spring.aop.foo;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 在实现ThrowsAdvice的时候，定义类似<i>void afterThrowing([Method, args, target], Thorwable e)</i>就行了。
 */
public class ExceptionBarrierThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Throwable t) {
        t.printStackTrace();
    }

    public void afterThrowing(RuntimeException e) {
        e.printStackTrace();
    }

    public void afterThrowing(Method m, Object[] args, Object target, Exception e) {
        e.printStackTrace();
    }
}
