package ru.wkn.server.util;

import ru.wkn.core.communication.MessageReader;
import ru.wkn.core.communication.MessageWriter;
import ru.wkn.core.requests.HandshakeRequest;
import ru.wkn.core.responses.HandshakeResponse;
import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.branchoffice.department.employee.Supervisor;
import ru.wkn.server.model.branchoffice.department.employee.Timekeeper;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.StringJoiner;

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
            workLogic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void workLogic() throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        String action = "";

        while (!action.equals("Exit")) {
            action = dataInputStream.readUTF();
            Employee employee = null;
            if (action.equals("Authorization")) {
                employee = logIn(action, dataInputStream);
            }
            String employeeInfo;
            if (employee != null) {
                employeeInfo = getInformation(employee);
                dataOutputStream.writeUTF(employeeInfo);
                String status = employee.getEmployeeStatusEnum().toString();
                dataOutputStream.writeUTF(status);
                Supervisor supervisor = new Supervisor(employee, modelFacade.getEmployeeManager(), modelFacade.getTimekeepingReport());
                Timekeeper timekeeper = new Timekeeper(employee, modelFacade.getDayManager(), modelFacade.getTaskManager(), modelFacade.getTimekeepingLog());
            }
            // other methods...
        }
        socket.close();
    }

    private String getInformation(Employee employee) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(employee.getName());
        stringJoiner.add(employee.getSurname());
        stringJoiner.add(employee.getTelephoneNumber());
        stringJoiner.add(employee.getEmployeeStatusEnum().toString());
        stringJoiner.add(employee.getEmployeeAuthorizationData().getLogin());
        stringJoiner.add(employee.getEmployeeAuthorizationData().getPassword());
        stringJoiner.add(employee.getDepartment().getDepartmentName());
        stringJoiner.add(employee.getDepartment().getBranchOffice().getBranchOfficeName());
        return stringJoiner.toString();
    }

    private Employee logIn(String action, DataInputStream dataInputStream) {
        if (action.equals("Authorization")) {
            String login = null;
            String password = null;
            try {
                login = dataInputStream.readUTF();
                password = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            modelFacade = new ModelFacade(new EmployeeAuthorizationData(login, password));
            return modelFacade.getEmployee();
        }
        return null;
    }
}
