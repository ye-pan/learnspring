package com.yp.spring.ioc;

import com.yp.spring.ioc.foo.Bar;
import com.yp.spring.ioc.foo.Foo;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BeanWrapperTest {

    @Test
    public void testBeanWrapper() {
        Foo foo = new Foo("hello world");
        Bar bar = new Bar();

        BeanWrapper wrapper = new BeanWrapperImpl(bar);
        wrapper.setPropertyValue("foo", foo);
        assertEquals(bar, wrapper.getWrappedInstance());
        assertEquals(foo, wrapper.getPropertyValue("foo"));
    }
}
