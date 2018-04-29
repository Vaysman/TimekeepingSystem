package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;
import ru.wkn.server.model.timekeeping.timekeepingunits.task.Task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TaskManagerPage extends Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public TaskManagerPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
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

    private synchronized void pageLogic() throws IOException, PersistentException {
        Page page;
        String action;
        do {
            synchronized (dataInputStream) {
                action = dataInputStream.readUTF();
            }
            switch (action) {
                case "CREATE_TASK": {
                    createTask();
                    break;
                }
                case "DELETE_TASK": {
                    deleteTask();
                    break;
                }
                case "DELETE_ALL": {
                    deleteAllTasks();
                    break;
                }
                case "UPDATE_TASK": {
                    updateTask();
                    break;
                }
                case "EXIT": {
                    page = new TimekeeperPage(modelFacade, dataInputStream, dataOutputStream);
                    break;
                }
                default: {
                    throw new PersistentException("COMMAND_NOT_EXIST");
                }
            }
        } while (!action.equals("EXIT"));
    }

    private synchronized void createTask() throws IOException, PersistentException {
        int employeeID = dataInputStream.readInt();
        String definition = dataInputStream.readUTF();
        String startTime = dataInputStream.readUTF();
        String endTime = dataInputStream.readUTF();
        String date = dataInputStream.readUTF();
        boolean isAccomplished = dataInputStream.readBoolean();
        modelFacade.getTimekeeper().getTaskManager().createTask(employeeID, definition, startTime, endTime, date, isAccomplished);
    }

    private synchronized void deleteTask() throws IOException, PersistentException {
        modelFacade.getTimekeeper().getTaskManager().deleteTask(getTask());
    }

    private synchronized void deleteAllTasks() throws PersistentException {
        modelFacade.getTimekeeper().getTaskManager().deleteAll();
    }

    private synchronized void updateTask() throws IOException, PersistentException {
        modelFacade.getTimekeeper().getTaskManager().editTask(getTask(), getTask());
    }

    private synchronized Task getTask() throws IOException {
        int taskID = dataInputStream.readInt();
        int employeeID = dataInputStream.readInt();
        String definition = dataInputStream.readUTF();
        String startTime = dataInputStream.readUTF();
        String endTime = dataInputStream.readUTF();
        String date = dataInputStream.readUTF();
        boolean isAccomplished = dataInputStream.readBoolean();
        return new Task(taskID, employeeID, definition, startTime, endTime, date, isAccomplished);
    }
}
