package com.test.ext;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserService implements SmartInitializingSingleton {

    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        System.out.println(event.getClass());
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("UserService.afterSingletonsInstantiated");
    }
}
