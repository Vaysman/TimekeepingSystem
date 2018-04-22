package ru.wkn.core.communication;

import ru.wkn.core.IMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageReader {

    public static final int HEADER_LENGTH = 12;
    private final DataInputStream DATA_INPUT_STREAM;

    public MessageReader(InputStream inputStream) {
        this.DATA_INPUT_STREAM = new DataInputStream(inputStream);
    }

    public UniqueMessage readMessage() throws IOException {
        int packageLength = DATA_INPUT_STREAM.readInt();
        if (packageLength < HEADER_LENGTH) {
            throw new IOException("Wrong package length");
        }
        byte[] buffer = new byte[packageLength - 4];
        DATA_INPUT_STREAM.readFully(buffer);
        DataInputStream messageInputStream = new DataInputStream(new ByteArrayInputStream(buffer));

        int uniqueId = messageInputStream.readInt();
        int messageId = messageInputStream.readInt();

        IMessage message = MessageFactory.createMessage(messageId);

        message.readExternal(messageInputStream);
        System.out.println("Message " + message.getClass().getName() + " received.");

        return new UniqueMessage(message, uniqueId);
    }

    public static class UniqueMessage {

        public final IMessage message;
        public final int uniqueId;

        private UniqueMessage(IMessage message, int uniqueId) {
            this.message = message;
            this.uniqueId = uniqueId;
        }
    }

}
