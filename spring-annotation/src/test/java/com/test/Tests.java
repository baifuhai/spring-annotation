package com.test;

import com.test.bean.Person;
import com.test.config.ColorFactoryBean;
import com.test.config.MainConfig;
import com.test.dao.PersonDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.security.AllPermission;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Tests {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void before() {
        //applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
    }

    @After
    public void after() {
        applicationContext.close();
    }

    @Test
    public void getBeanDefinitionNames() {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void test01() {
        Person person = applicationContext.getBean(Person.class);
        Person person2 = applicationContext.getBean(Person.class);
        System.out.println(person);
        System.out.println(person2);
        System.out.println(person == person2);

        PersonDao personDao = applicationContext.getBean(PersonDao.class);
        System.out.println(personDao);
    }

    @Test
    public void test02() {
        Map<String, Person> personList = applicationContext.getBeansOfType(Person.class);
        for (Map.Entry<String, Person> e : personList.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    @Test
    public void test03() {
        Map<String, String> env = System.getenv();
        for (Map.Entry<String, String> e : env.entrySet()) {
            System.out.println(e.getKey() + "\t\t\t " + e.getValue());
        }
    }

    @Test
    public void test04() {
        Properties ps = System.getProperties();
        for (Map.Entry<Object, Object> e : ps.entrySet()) {
            System.out.println(e.getKey() + "\t\t\t " + e.getValue());
        }
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
    }

    @Test
    public void test05() {
        Environment env = applicationContext.getEnvironment();
        System.out.println(env.getProperty("os.name"));
    }

    @Test
    public void test06() {
        Object bean = applicationContext.getBean("colorFactoryBean");
        System.out.println(bean.getClass());

        Object bean2 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(bean2.getClass());

        ColorFactoryBean factoryBean = applicationContext.getBean(ColorFactoryBean.class);
        System.out.println(factoryBean.getClass());
    }

    @Test
    public void test011(){
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
    }

}
