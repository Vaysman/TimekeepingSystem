package ru.wkn.client.windows.container;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Container {

    private static List<String> strings = new ArrayList<>();
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    public static List<String> getStrings() {
        return strings;
    }

    public static void clearContainerStrings() {
        strings = new ArrayList<>();
    }

    public static DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public static void setDataInputStream(DataInputStream dataInputStream) {
        Container.dataInputStream = dataInputStream;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public static void setDataOutputStream(DataOutputStream dataOutputStream) {
        Container.dataOutputStream = dataOutputStream;
    }
}
