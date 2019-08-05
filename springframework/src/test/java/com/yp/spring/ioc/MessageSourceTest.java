package com.yp.spring.ioc;

import org.junit.Test;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

public class MessageSourceTest {

    @Test
    public void testStaticMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("menu.file", Locale.US, "File");
        System.out.println(messageSource.getMessage("menu.file", new Object[]{"F"}, Locale.US));
    }
}
