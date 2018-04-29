package ru.wkn.server.pages;

import ru.wkn.server.pages.container.Container;
import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EmployeeInformationPage extends Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public EmployeeInformationPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
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
        synchronized (dataOutputStream) {
            dataOutputStream.writeUTF(Container.readEmployeeInformation(modelFacade.getEmployee()));
        }
        String action;
        Page page;
        synchronized (dataInputStream) {
            action = dataInputStream.readUTF();
        }
        switch (action) {
            case "EXIT":{
                page = new EmployeePage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            default: {
                throw new PersistentException("COMMAND_NOT_EXIST");
            }
        }
    }
}
