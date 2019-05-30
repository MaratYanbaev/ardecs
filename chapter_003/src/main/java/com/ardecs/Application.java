package com.ardecs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myContext.xml");
        UserBean user = context.getBean("userBean", UserBean.class);
        user.getTuTu();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(UserConfiguration.class);
        user = ctx.getBean(UserBean.class);
        user.getTuTu();

        context.close();
    }
}
