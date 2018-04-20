package ru.wkn.server.util;

import ru.wkn.core.communication.MessageReader;
import ru.wkn.core.communication.MessageWriter;
import ru.wkn.core.requests.HandshakeRequest;
import ru.wkn.core.responses.HandshakeResponse;

import java.io.IOException;
import java.net.Socket;

public class ClientSession extends Thread {
    private final Socket socket;
    private final MessageReader reader;
    private final MessageWriter writer;
    private final Context context;

    public ClientSession(final Socket socket, final Context context) throws IOException {
        this.socket = socket;
        this.reader = new MessageReader(socket.getInputStream());
        this.writer = new MessageWriter(socket.getOutputStream());
        this.context = context;
    }

    public void run() {
        MessageReader.UniqueMessage msg;
        try {
            msg = reader.readMessage();

            //Рукопожатие
            if(msg.message instanceof HandshakeRequest) {
                if(((HandshakeRequest)msg.message).match()) {
                    writer.writeResponse(new HandshakeResponse(), msg.uniqueId);
                }
            }

            //Обменялись рукопожатиями, начинаем работу
            this.doWork();

            //выход
            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {}
}
