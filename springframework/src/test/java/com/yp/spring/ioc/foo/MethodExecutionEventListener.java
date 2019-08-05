package com.yp.spring.ioc.foo;

import java.util.EventListener;

public interface MethodExecutionEventListener extends EventListener {
    void onMethodBegin(MethodExecutionEvent event);

    void onMethodEnd(MethodExecutionEvent event);
}
