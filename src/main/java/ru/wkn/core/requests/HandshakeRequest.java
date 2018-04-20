package ru.wkn.core.requests;

import ru.wkn.core.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HandshakeRequest extends Request {

    public static final String HANDSHAKE_STRING = "handshake request";
    private String handshake;

    @Override
    public void readExternal(DataInputStream dataInputStream) throws IOException {
        handshake = dataInputStream.readUTF();
    }

    @Override
    public void writeExternal(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(HANDSHAKE_STRING);
    }

    public boolean match() {
        return HANDSHAKE_STRING.equals(handshake);
    }

}

