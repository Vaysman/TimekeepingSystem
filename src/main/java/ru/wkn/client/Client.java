package ru.wkn.client;

import javafx.scene.control.Alert;
import ru.wkn.client.windows.SupervisorWindow;
import ru.wkn.client.windows.TimekeeperWindow;
import ru.wkn.client.windows.Window;
import ru.wkn.client.windows.container.Container;
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

            Container.setDataInputStream(dataInputStream);
            Container.setDataOutputStream(dataOutputStream);

            try {
                logIn(dataInputStream, dataOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            writeMessage("Error", e.getMessage());
        }
    }

    public void logIn(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws Exception {
        Window window;
        String login = Container.getStrings().get(0);
        dataOutputStream.writeUTF(login);
        String password = Container.getStrings().get(1);
        Container.clearContainerStrings();
        dataOutputStream.writeUTF(password);
        String status = dataInputStream.readUTF();
        switch (status) {
            case "EMPLOYEE": {
                window = new EmployeeWindow();
                break;
            }
            case "SUPERVISOR": {
                window = new SupervisorWindow();
                break;
            }
            case "TIMEKEEPER": {
                window = new TimekeeperWindow();
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
