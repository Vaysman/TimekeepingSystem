package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TimekeepingEventManagerPage extends Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public TimekeepingEventManagerPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        super(modelFacade, dataInputStream, dataOutputStream);
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        try {
            pageLogic();
        } catch (IOException | PersistentException e) {
            e.printStackTrace();
        }
    }

    private void pageLogic() throws IOException, PersistentException {
        Page page;
        String action;
        do {
            synchronized (dataInputStream) {
                action = dataInputStream.readUTF();
            }
            switch (action) {
                case "CREATE_EVENT": {
                    createEvent();
                    break;
                }
                case "EXIT": {
                    page = new EmployeePage(modelFacade, dataInputStream, dataOutputStream);
                    break;
                }
                default: {
                    throw new PersistentException("COMMAND_NOT_EXIST");
                }
            }
        } while (!action.equals("EXIT"));
    }

    private synchronized void createEvent() throws IOException {
        int employeeID = modelFacade.getEmployee().getEmployeeID();
        String type = dataInputStream.readUTF();
        String time = dataInputStream.readUTF();
        String date = dataInputStream.readUTF();
        modelFacade.getEmployee().getTimekeepingEventManager().createEvent(employeeID, type, time, date);
    }
}
