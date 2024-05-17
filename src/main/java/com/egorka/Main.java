package com.egorka;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.egorka.service.Menu;

public class Main {
    public static void main(String[] args)
    {
        Menu.menu();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }
}