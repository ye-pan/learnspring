package com.yp.spring.aop;

import com.yp.spring.aop.foo.Executable;
import com.yp.spring.aop.foo.PerformanceMethodInterceptor;
import com.yp.spring.aop.foo.SampleTask;
import com.yp.spring.aop.foo.Task;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class ProxyFactoryTest {

    @Test
    public void testFactoryAopProxyInterface() {
        SampleTask sampleTask = new SampleTask();
        ProxyFactory weaver = new ProxyFactory(sampleTask);
        //weaver.setInterfaces(Task.class);
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());
        weaver.addAdvisor(advisor);
        Task task = (Task)weaver.getProxy();
        task.execute(null);
        System.out.println(task.getClass());
    }

    @Test
    public void testFactoryAopProxyClass() {
        ProxyFactory weaver = new ProxyFactory(new Executable());
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());
        weaver.addAdvisor(advisor);
        Executable executable = (Executable)weaver.getProxy();
        executable.execute();
        System.out.println(executable.getClass());
    }

    @Test
    public void testSetProxyTargetClass() {
        ProxyFactory weaver = new ProxyFactory(new SampleTask());
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());
        weaver.addAdvisor(advisor);
        weaver.setProxyTargetClass(true);//强制spring开启代理类
        Task task = (Task)weaver.getProxy();
        task.execute(null);
        System.out.println(task.getClass());
    }
}
