package com.yp.spring.ioc.foo;

public class Bar {
    private Foo foo;

    public void setFoo(Foo foo) {
        this.foo = foo;
    }
    public Foo getFoo() {
        return this.foo;
    }
}
