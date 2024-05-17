package com.egorka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.Scanner;
@Component
public class ControlPanelProxyImpl implements ControlPanelProxy{
    private final ControlPanel controlPanel;
    private final Logger logger;
    private final Map<String, String> cache; // Кэш для хранения результатов операций
    private final Menu menu;
    private boolean initialized = false;

    @Autowired
    public ControlPanelProxyImpl(ControlPanel controlPanel, @Autowired ApplicationContext context) {
        this.controlPanel = controlPanel;
        this.logger = Logger.getLogger(ControlPanelProxyImpl.class.getName());
        this.cache = new HashMap<>();
        this.menu = context.getBean(Menu.class); // Получаем бин Menu из контекста
        if (checking()) {
            System.out.println("С возвращением, пользователь!!!");
        } else{
            System.out.println("ИМПОСТЕР!!! Доступ закрыт! Программа отключается...");
            System.exit(0); // Завершаем программу
        }
    }

    private void initialize() {
        if (!initialized) {
            logger.info("Инициализация панели управления...");
            initialized = true;
        }
    }

    @Override
    public void visualize() {
        initialize();
        controlPanel.visualize();
        cache.put("visualize", "Панель управления отображена");
    }

    @Override
    public void pressButton(int x, int y) {
        if (checkAccess()) {
            logger.info(String.format("Кнопка нажата в координатах (%s, %s)...", x, y));
            controlPanel.pressButton(x, y);
            logger.info("Панель после нажатия кнопки:");
            cache.put("pressButton", "Кнопка нажата"); // Сохраняем результат операции в кэше
        } else {
            logger.warning("Отказано в доступе");
        }
    }

    @Override
    public void requestLampBinding() {
        if (checkAccess()) {
            logger.info("Запрос на привязку лампы...");
            controlPanel.requestLampBinding();
            logger.info("Панель после попытки привязки:");
            cache.put("requestLampBinding", "Лампа привязана к кнопке"); // Сохраняем результат операции в кэше
        } else {
            logger.warning("Отказано в доступе");
        }
    }

    @Override
    public void requestLampUnlink() {
        Scanner scanner = new Scanner(System.in);
        int buttonX = Menu.requestIntegerInput(scanner, "Введите координату кнопки (X):");
        int buttonY = Menu.requestIntegerInput(scanner, "Введите координату кнопки (Y):");

        int lampX = Menu.requestIntegerInput(scanner, "Введите координату лампы (X):");
        int lampY = Menu.requestIntegerInput(scanner, "Введите координату лампы (Y):");

        if (checkAccess()) {
            logger.info("Запрос на отвязку лампы...");
            controlPanel.unlinkButtonFromLamp(buttonX, buttonY, lampX, lampY);
            logger.info("Панель после попытки отвязки:");
            cache.put("unlinkButtonFromLamp", "Лампа отвязана от кнопки"); // Сохраняем результат операции в кэше
        }
        else {
            logger.warning("Отказано в доступе");
        }
    }

    private boolean checkAccess() {
        return true;
    }

    private boolean checking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Но сначала удостоверимся, что Вы не посторонний пользователь :)");
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        // Проверяем пароль
        return validatePassword(password);
    }

    private boolean validatePassword(String password) {
        return password.equals("l1");
    }

    public void shutdown() {
        if (initialized) {
            logger.info("Завершение работы панели управления...");
            initialized = false;
        }
    }
}
