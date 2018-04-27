package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TimekeepingReportPage extends Page {

    private ModelFacade modelFacade;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public TimekeepingReportPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        super(modelFacade, dataInputStream, dataOutputStream);
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        pageLogic();
    }

    private void pageLogic() {
        //
    }
}
