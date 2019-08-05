package com.yp.spring.ioc.foo;

public class Foo {
    private String name;

    public Foo(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
