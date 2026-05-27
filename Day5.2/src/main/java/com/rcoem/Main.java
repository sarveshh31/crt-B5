package com.rcoem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("appConfig.xml");

        Emp emp = (Emp) context.getBean("emp1");

        System.out.println(emp);
    }
}