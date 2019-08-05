package com.yp.spring.aop.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestCtrlInvocationHandler implements InvocationHandler {
    private static final Logger log = LoggerFactory.getLogger(RequestCtrlInvocationHandler.class);

    private Object target;

    public RequestCtrlInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("request")) {
            log.info("proxy...");
            return method.invoke(target, args);
        }
        return null;
    }
}
