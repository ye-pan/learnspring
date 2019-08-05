package com.yp.spring.ioc;

import com.yp.spring.ioc.foo.SimpleBeanFactoryPostProcessor;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

    @Test
    public void testBeanFactoryPostProcessor() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:sample-applicationContext.xml");
        ac.addBeanFactoryPostProcessor(new SimpleBeanFactoryPostProcessor());
        ac.getBean("foo");
    }
}
