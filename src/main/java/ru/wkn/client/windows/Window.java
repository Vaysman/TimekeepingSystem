package ru.wkn.client.windows;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Window {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Window(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }
}
