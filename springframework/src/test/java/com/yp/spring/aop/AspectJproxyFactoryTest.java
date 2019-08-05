package com.yp.spring.aop;

import com.yp.spring.aop.aspect.PerformanceTraceAspect;
import com.yp.spring.aop.foo.Foo;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectJproxyFactoryTest {

    @Test
    public void testAspectJAround() {
        AspectJProxyFactory weaver = new AspectJProxyFactory();
        weaver.setProxyTargetClass(true);
        weaver.setTarget(new Foo());
        weaver.addAspect(PerformanceTraceAspect.class);
        Foo proxy = weaver.getProxy();
        proxy.method1();
        proxy.method2();
        proxy.method3();
    }
}
