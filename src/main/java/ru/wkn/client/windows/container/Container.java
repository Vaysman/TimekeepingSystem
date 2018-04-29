package ru.wkn.client.windows.container;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private static List<String> strings = new ArrayList<>();

    public static List<String> getStrings() {
        return strings;
    }

    public static void clearContainer() {
        strings = new ArrayList<>();
    }
}
