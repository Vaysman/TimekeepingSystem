package ru.wkn.server.util;

import ru.wkn.core.communication.MessageReader;
import ru.wkn.core.communication.MessageWriter;
import ru.wkn.core.requests.HandshakeRequest;
import ru.wkn.core.responses.HandshakeResponse;
import ru.wkn.server.model.ModelFacade;

import java.io.IOException;
import java.net.Socket;

public class ClientSession extends Thread {

    private final Socket socket;
    private final MessageReader reader;
    private final MessageWriter writer;
    private final Context context;
    private ModelFacade modelFacade;

    public ClientSession(final Socket socket, final Context context) throws IOException {
        this.socket = socket;
        this.reader = new MessageReader(socket.getInputStream());
        this.writer = new MessageWriter(socket.getOutputStream());
        this.context = context;
    }

    @Override
    public void run() {
        MessageReader.UniqueMessage uniqueMessage;
        try {
            uniqueMessage = reader.readMessage();
            if(uniqueMessage.message instanceof HandshakeRequest) {
                if(((HandshakeRequest) uniqueMessage.message).match()) {
                    writer.writeResponse(new HandshakeResponse(), uniqueMessage.uniqueId);
                }
            }
            this.doWork();
            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        // work logic...
    }
}
