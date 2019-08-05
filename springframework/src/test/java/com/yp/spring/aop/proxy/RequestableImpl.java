package com.yp.spring.aop.proxy;

public class RequestableImpl implements IRequestable {
    @Override
    public void request() {
        System.out.println("some request...");
    }


}
