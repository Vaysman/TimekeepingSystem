package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;
import ru.wkn.server.pages.container.Container;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EmployeePage extends Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public EmployeePage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
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
        String action;
        Page page;
        synchronized (dataInputStream) {
            action = dataInputStream.readUTF();
        }
        switch (action) {
            case "INFO": {
                synchronized (dataOutputStream) {
                    dataOutputStream.writeUTF(Container.readEmployeeInformation(modelFacade.getEmployee()));
                }
                break;
            }
            case "EVENT_MANAGER": {
                page = new TimekeepingEventManagerPage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            case "TIMEKEEPER": {
                page = new TimekeeperPage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            case "SUPERVISOR": {
                page = new SupervisorPage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            case "EXIT": {
                page = new AuthorizationPage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            default: {
                throw new PersistentException("COMMAND_NOT_EXIST");
            }
        }
    }
}
