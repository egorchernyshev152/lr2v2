// Menu.java
package com.egorka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final ControlPanelProxy proxy;
    private final RequestHandlerChain handlerChain;

@Autowired
    public Menu(@Qualifier("controlPanel") ControlPanelProxy proxy, RequestHandlerChain handlerChain) {
        this.proxy = proxy;
        this.handlerChain = handlerChain;

    }

    public void start() {
        System.out.println("Добро пожаловать в программу управления!");
        System.out.println("                /\\_/\\");
        System.out.println("               ( o.o )");
        System.out.println("                > ^ <");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        int width = requestIntegerInput(scanner, "Введите ширину панели:");
        int height = requestIntegerInput(scanner, "Введите высоту панели:");

        System.out.println("Сгенерирована панель управления:");
        proxy.visualize();

        while (true) {
            System.out.println("          Меню:");
            System.out.println("1. Нажать на кнопку");
            System.out.println("2. Привязать кнопку к лампе");
            System.out.println("3. Отвязать кнопку от лампы");
            System.out.println("4. Выйти");

            int choice = requestIntegerInput(scanner, "Ваше действие:");
            handlerChain.handleRequest(choice, proxy, scanner);

            if (choice == 4) {
                System.out.println("До свидания!");
                break;
            }
        }
    }

    public static int requestIntegerInput(Scanner scanner, String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Неверный ввод. Пожалуйста, введите целое число:");
            scanner.next(); // Пропускаем неверный ввод
        }
        return scanner.nextInt();
    }
}


