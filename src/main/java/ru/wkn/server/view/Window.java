package ru.wkn.server.view;

public abstract class Window {

    private String uniqueMessage;

    public Window(String uniqueMessage) {
        this.uniqueMessage = uniqueMessage;
    }

    public String getBaseMessage() {
        return "0. Выход из аккаунта\n" +
                "1. Мои события\n" +
                "2. Новое событие\n" +
                "3. Поиск сотрудников\n" +
                "4. Мои задачи";
    }

    public String getUniqueMessage() {
        return uniqueMessage;
    }
}
