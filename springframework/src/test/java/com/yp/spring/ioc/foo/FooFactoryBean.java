package com.yp.spring.ioc.foo;

import org.springframework.beans.factory.FactoryBean;

public class FooFactoryBean implements FactoryBean<Foo> {
    @Override
    public Foo getObject() throws Exception {
        return new Foo("hello world");
    }

    @Override
    public Class<?> getObjectType() {
        return Foo.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
