package com.ardecs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("myContext.xml");
        User user = (User) context.getBean("user");
        user.getTuTu();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfiguration.class);
        user = ctx.getBean(User.class);
        user.getTuTu();
    }
}
