package ru.wkn.client;

import ru.wkn.core.communication.MessageReader;
import ru.wkn.core.communication.MessageReader.UniqueMessage;
import ru.wkn.core.communication.MessageWriter;
import ru.wkn.core.requests.HandshakeRequest;
import ru.wkn.core.responses.HandshakeResponse;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private final InetAddress host;
    private final int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(host, port)) {
            MessageReader reader = new MessageReader(socket.getInputStream());
            MessageWriter writer = new MessageWriter(socket.getOutputStream());

            writer.writeRequest(new HandshakeRequest());

            UniqueMessage uniqueMessage = reader.readMessage();

            if (!(uniqueMessage.message instanceof HandshakeResponse)) {
                return;
            }

            boolean stopFlag = false;
            do {
                logicStart(stopFlag);
            } while (!stopFlag);

            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logicStart(boolean stopFlag) {
        // work logic...
    }
}
