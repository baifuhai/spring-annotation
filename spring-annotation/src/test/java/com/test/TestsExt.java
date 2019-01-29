package com.test;

import com.test.ext.ExtConfig;
import com.test.ext.MyApplicationEvent;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestsExt {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        applicationContext.publishEvent(new MyApplicationEvent(123));

        applicationContext.close();
    }

}
