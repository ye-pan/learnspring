package com.yp.spring.ioc.foo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MethodExecutionEventPublisher {

    private List<MethodExecutionEventListener> listeners = new ArrayList<>();

    public void methodToMonitor() {
        MethodExecutionEvent event = new MethodExecutionEvent(this, "methodToMonitor");
        publishEvent(MethodExecutionStatus.BEGIN, event);
        //...
        publishEvent(MethodExecutionStatus.END, event);
    }

    protected void publishEvent(final MethodExecutionStatus status, final MethodExecutionEvent event) {
        listeners.forEach(listener->{
            if(status == MethodExecutionStatus.BEGIN) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        });
    }

    enum MethodExecutionStatus {
        BEGIN, END;
    }

    public void addListener(MethodExecutionEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MethodExecutionEventListener listener) {
        listeners.remove(listener);
    }

    public void removeAll() {
        listeners.clear();
    }

    @Test
    public void test() {
        MethodExecutionEventPublisher publisher = new MethodExecutionEventPublisher();
        publisher.addListener(new SimpleMethodExecutionEventListener());
        publisher.methodToMonitor();
    }
}
