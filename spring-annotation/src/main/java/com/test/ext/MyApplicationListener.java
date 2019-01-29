package com.test.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    //当容器中发布此事件以后，方法触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextStartedEvent) {
            System.out.println("ContextStartedEvent...");
        } else if (event instanceof ContextStoppedEvent) {
            System.out.println("ContextStoppedEvent...");
        } else if (event instanceof ContextRefreshedEvent) {
            System.out.println("ContextRefreshedEvent...");
        } else if (event instanceof ContextClosedEvent) {
            System.out.println("ContextClosedEvent...");
        } else if (event instanceof MyApplicationEvent) {
            System.out.println("MyApplicationEvent...");
        }
    }

}
