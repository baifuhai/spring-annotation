package com.test;

import com.test.config.MainConfigOfTx;
import com.test.service.PersonService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestsTx {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfTx.class);

        PersonService personService = applicationContext.getBean(PersonService.class);
        personService.insert();

        applicationContext.close();
    }

}
