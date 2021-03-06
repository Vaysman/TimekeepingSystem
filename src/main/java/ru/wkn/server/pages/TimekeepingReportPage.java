package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimekeepingReportPage extends Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public TimekeepingReportPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
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
        do {
            synchronized (dataInputStream) {
                action = dataInputStream.readUTF();
            }
            switch (action) {
                case "GET_DAY": {
                    writeDayReport();
                    break;
                }
                case "EXIT": {
                    break;
                }
                default:
                    throw new PersistentException("COMMAND_NOT_EXIST");
            }
        } while (!action.equals("EXIT"));
    }

    private synchronized void writeDayReport() throws PersistentException, IOException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        dataOutputStream.writeUTF(modelFacade.getSupervisor().getTimekeepingReport().getDayReport(formatForDateNow.format(dateNow)).toString());
    }
}
