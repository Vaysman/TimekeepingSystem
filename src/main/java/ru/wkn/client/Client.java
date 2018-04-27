package ru.wkn.client;

import javafx.scene.control.Alert;
import ru.wkn.client.windows.Container;
import ru.wkn.client.windows.EmployeeWindow;
import ru.wkn.core.communication.MessageReader;
import ru.wkn.core.communication.MessageReader.UniqueMessage;
import ru.wkn.core.communication.MessageWriter;
import ru.wkn.core.requests.HandshakeRequest;
import ru.wkn.core.responses.HandshakeResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            while (true) {
                logIn(dataInputStream, dataOutputStream);
            }

        } catch (IOException e) {
            writeMessage("Error", e.getMessage());
        }
    }

    public void logIn(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF("Authorization");
        String login = Container.getLogin();
        dataOutputStream.writeUTF(login);
        String password = Container.getPassword();
        dataOutputStream.writeUTF(password);
        String employeeInfo = dataInputStream.readUTF();
        String status = dataInputStream.readUTF();
        switch (status) {
            case "EMPLOYEE": {
                try {
                    EmployeeWindow employeeWindow = new EmployeeWindow(employeeInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "SUPERVISOR": {
                try {
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "TIMEKEEPER": {
                try {
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                writeMessage("Ошибка", "Данной записи не существует");
            }
        }
    }

    private void writeMessage(String name, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(name);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
