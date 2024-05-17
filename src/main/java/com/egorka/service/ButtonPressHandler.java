package com.egorka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.egorka.service.Menu.requestIntegerInput;
@Component
public class ButtonPressHandler implements RequestHandler {
    private final Menu menu;

    @Autowired
    public ButtonPressHandler(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void handleRequest(int choice, ControlPanelProxy proxy, Scanner scanner) {
        if (choice == 1) {
            int x = requestIntegerInput(scanner, "Введите координату кнопки (X):");
            int y = requestIntegerInput(scanner, "Введите координату кнопки (Y):");
            proxy.pressButton(x, y);
            System.out.println("Новое состояние панели:");
            proxy.visualize();
        } else {
            System.out.println("Передача запроса следующему обработчику...");
        }
    }
}
