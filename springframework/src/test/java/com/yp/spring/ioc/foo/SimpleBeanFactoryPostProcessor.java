package com.yp.spring.ioc.foo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactory需要自己手动编码，而ApplicationContext实现自动执行该类
 */
public class SimpleBeanFactoryPostProcessor implements BeanFactoryPostProcessor  {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(".......");
    }
}
