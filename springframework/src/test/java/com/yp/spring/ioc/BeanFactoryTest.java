package com.yp.spring.ioc;

import com.yp.spring.ioc.foo.Bar;
import com.yp.spring.ioc.foo.Foo;
import com.yp.spring.ioc.foo.FooFactoryBean;
import com.yp.spring.ioc.foo.MethodInjectionBar;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.SimpleThreadScope;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    @Test
    public void testRegisterBean() {
        String text = "test text";
        BeanDefinitionRegistry registry = new DefaultListableBeanFactory();

        BeanDefinition fooBean = new RootBeanDefinition(Foo.class);
        ConstructorArgumentValues constructorDi = new ConstructorArgumentValues();
        constructorDi.addIndexedArgumentValue(0, text);
        ((RootBeanDefinition) fooBean).setConstructorArgumentValues(constructorDi);
        registry.registerBeanDefinition("foo", fooBean);
        fooBean.setScope(BeanDefinition.SCOPE_SINGLETON);
        assertNotNull(((DefaultListableBeanFactory) registry).getBean(Foo.class));
        assertEquals(text, ((DefaultListableBeanFactory) registry).getBean(Foo.class).getName());

        BeanDefinition barBean = new RootBeanDefinition(Bar.class);
        barBean.setScope(BeanDefinition.SCOPE_SINGLETON);
        MutablePropertyValues propertyDi = new MutablePropertyValues();
        propertyDi.addPropertyValue(new PropertyValue("foo", fooBean));
        ((RootBeanDefinition) barBean).setPropertyValues(propertyDi);
        registry.registerBeanDefinition("bar", barBean);
        assertNotNull(((DefaultListableBeanFactory) registry).getBean(Bar.class));
        //TODO 排查为何BeanFactory的编码注入不是单例
        assertEquals(((DefaultListableBeanFactory) registry).getBean(Foo.class), ((DefaultListableBeanFactory) registry).getBean(Bar.class).getFoo());
    }


    @Test
    public void testLoadBeanFromXml() {
        BeanDefinitionRegistry registry = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(registry);
        beanDefinitionReader.loadBeanDefinitions("classpath:sample-beans-dtd.xml");

        assertNotNull(((DefaultListableBeanFactory) registry).getBean(Foo.class));
        assertEquals(((DefaultListableBeanFactory) registry).getBean(Foo.class).getName(), "hello world");

        assertNotNull(((DefaultListableBeanFactory) registry).getBean(Bar.class));
        assertEquals(((DefaultListableBeanFactory) registry).getBean(Foo.class), ((DefaultListableBeanFactory) registry).getBean(Bar.class).getFoo());
    }

    @Test
    public void testRegisterThreadScope() {
        ConfigurableBeanFactory configurable = new DefaultListableBeanFactory();
        configurable.registerScope("thread", new SimpleThreadScope());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)configurable);
        beanDefinitionReader.loadBeanDefinitions("classpath:sample-beans-xsd.xml");

        Foo foo1 = configurable.getBean(Foo.class);
        new Thread(() -> {
            assertNotEquals(foo1, configurable.getBean(Foo.class));
            synchronized (this) {
                notify();
            }
        }).start();

        assertEquals(foo1, configurable.getBean(Foo.class));
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFactoryBean() {
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        ((DefaultListableBeanFactory) beanFactory).registerSingleton("foo", new FooFactoryBean());

        Foo foo1 = beanFactory.getBean(Foo.class);
        assertNotNull(foo1);
        assertNotEquals(foo1, beanFactory.getBean(Foo.class));

        assertFalse(beanFactory.getBean("foo") instanceof FooFactoryBean);
        assertTrue(beanFactory.getBean("&foo") instanceof FooFactoryBean);
    }

    @Test
    public void testMethodInjection() {
        BeanDefinitionRegistry beanFactory = new DefaultListableBeanFactory();
        ((DefaultListableBeanFactory) beanFactory).registerScope("thread", new SimpleThreadScope());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:sample-beans-xsd.xml");
        MethodInjectionBar bar = ((DefaultListableBeanFactory) beanFactory).getBean(MethodInjectionBar.class);
        assertNotEquals(bar.getBar(), bar.getBar());
    }
}
