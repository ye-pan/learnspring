package com.yp.spring.aop.foo;

public class SampleTask implements Task {
    @Override
    public void execute(TaskExecutionContext ctx) {
        System.out.println("task executed.");
    }
}
