package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TimekeeperPage extends Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public TimekeeperPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
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
            case "EMPLOYEE":{
                page = new EmployeePage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            case "TASK_MANAGER": {
                page = new TaskManagerPage(modelFacade, dataInputStream, dataOutputStream);
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
