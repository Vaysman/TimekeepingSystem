package ru.wkn.core.responses;

import ru.wkn.core.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HandshakeResponse extends Response {

    public static final String HANDSHAKE_RESPONSE_STRING = "handshake response";
    private String handshake;

    @Override
    public void readExternal(DataInputStream dataInputStream) throws IOException {
        handshake = dataInputStream.readUTF();
    }

    @Override
    public void writeExternal(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(HANDSHAKE_RESPONSE_STRING);
    }

    public boolean match() {
        return HANDSHAKE_RESPONSE_STRING.equals(handshake);
    }

}

