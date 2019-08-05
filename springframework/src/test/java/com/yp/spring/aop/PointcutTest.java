package com.yp.spring.aop;

import org.junit.Test;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class PointcutTest {


    @Test
    public void testJdkRegexpMathodPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*testJdkRegexpMathodPointcut");
        Method method = ClassUtils.getMethod(PointcutTest.class, "testJdkRegexpMathodPointcut");
        assertTrue(pointcut.matches(method, PointcutTest.class));
    }

    @Test
    public void testAnnotationMatchingPointcut() {
        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Test.class);
        Method method = ClassUtils.getMethod(PointcutTest.class, "testAnnotationMatchingPointcut");
        assertTrue(pointcut.getMethodMatcher().matches(method, PointcutTest.class));
    }

    @Test
    public void testNameMatchMethodPointcut() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("wait", "notify", "notifyAll", "toString", "hashCode");
        Method method = ClassUtils.getMethod(Object.class, "wait");
        assertTrue(pointcut.matches(method, Object.class));

        method = ClassUtils.getMethod(Object.class, "equals");
        assertFalse(pointcut.matches(method, Object.class));
    }
}
