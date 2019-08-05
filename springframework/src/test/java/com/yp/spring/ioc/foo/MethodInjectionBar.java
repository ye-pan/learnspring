package com.yp.spring.ioc.foo;

public class MethodInjectionBar {
    private Bar bar;
    public Bar getBar() {
        return bar;
    }
    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
