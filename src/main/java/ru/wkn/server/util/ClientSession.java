package ru.wkn.server.util;

import ru.wkn.core.communication.MessageReader;
import ru.wkn.core.communication.MessageWriter;
import ru.wkn.core.requests.HandshakeRequest;
import ru.wkn.core.responses.HandshakeResponse;
import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;

import java.io.*;
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

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String action = String.valueOf(1);

            BufferedReader keyBoard = new BufferedReader(new InputStreamReader(System.in));

            while (!action.equals("Exit")) {
                dataOutputStream.writeUTF("Enter your command:\n");
                Employee employee = null;
                if (action.equals("Authorization")) {
                    employee = logIn(action, dataInputStream, dataOutputStream);
                }
                // other methods...
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Employee logIn(String action, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        if (action.equals("Authorization")) {
            String login = null;
            String password = null;
            try {
                dataOutputStream.writeUTF("Enter your login:\n");
                login = dataInputStream.readUTF();
                dataOutputStream.writeUTF("Enter your password:\n");
                password = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            modelFacade = new ModelFacade(new EmployeeAuthorizationData(login, password));
            return modelFacade.logIn();
        }
        return null;
    }
}
