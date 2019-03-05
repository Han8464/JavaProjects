package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.BusinessPerson;
import com.springboot.chapter3.pojo.User;
import com.springboot.chapter3.pojo.definition.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.apache.log4j.Logger;

public class IoCTest {
    private static Logger log = Logger.getLogger(IoCTest.class);
    public static void main(String args[])
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = ctx.getBean(BusinessPerson.class);
        person.service();
        ctx.close();
    }
}
