package ru.wkn.core.communication;

import ru.wkn.core.IMessage;
import ru.wkn.core.Request;
import ru.wkn.core.Response;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MessageWriter {

    private static final int INITIAL_BUFFER_SIZE = 128;
    private final DataOutputStream dataOutputStream;
    private Integer requestIdCounter = 0;

    public MessageWriter(OutputStream outputStream) {
        this.dataOutputStream = new DataOutputStream(outputStream);
    }

    private int getNewRequestId() {
        synchronized (requestIdCounter) {
            return ++requestIdCounter;
        }
    }

    private void writeMessage(final IMessage message, final int uniqueId) throws IOException {
        int messageId = MessageFactory.getMessageId(message);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(INITIAL_BUFFER_SIZE);
        message.writeExternal(new DataOutputStream(byteArrayOutputStream));
        int messageLength = byteArrayOutputStream.size() + MessageReader.HEADER_LENGTH;

        synchronized (dataOutputStream) {
            dataOutputStream.writeInt(messageLength);
            dataOutputStream.writeInt(uniqueId);
            dataOutputStream.writeInt(messageId);
            byteArrayOutputStream.writeTo(dataOutputStream);
            dataOutputStream.flush();
        }
        System.out.println("Message " + message.getClass().getName() + " sent.");
    }

    public int writeRequest(final Request request) throws IOException {
        int uniqueId = getNewRequestId();
        writeMessage(request, uniqueId);
        return uniqueId;
    }

    public void writeResponse(final Response response, int requestId)
            throws IOException {
        writeMessage(response, requestId);
    }

}
