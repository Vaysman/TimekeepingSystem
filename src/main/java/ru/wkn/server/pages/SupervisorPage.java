package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SupervisorPage extends Page {

    private ModelFacade modelFacade;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public SupervisorPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        super(modelFacade, dataInputStream, dataOutputStream);
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        try {
            pageLogic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pageLogic() throws IOException {
        Page page;
        switch (dataInputStream.readUTF()) {
            case "EMPLOYEE_MANAGER": {
                page = new EmployeeManagerPage(modelFacade, dataInputStream, dataOutputStream);
                break;
            }
            case "TIMEKEEPING_REPORT": {
                page = new TimekeepingReportPage(modelFacade, dataInputStream, dataOutputStream);
            }
        }
    }
}
