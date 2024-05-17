// Main.java
package com.egorka;

import com.egorka.service.Menu;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Menu menu = context.getBean("menu", Menu.class);
        menu.start();
        context.close();
    }
}
