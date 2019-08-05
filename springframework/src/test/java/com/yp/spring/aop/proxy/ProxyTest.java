package com.yp.spring.aop.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    public void testRequest() {
        IRequestable requestable = (IRequestable)Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{IRequestable.class},
                new RequestCtrlInvocationHandler(new RequestableImpl()));
        requestable.request();
    }
}
