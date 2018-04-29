package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Page {

    private ModelFacade modelFacade;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public Page(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public ModelFacade getModelFacade() {
        return modelFacade;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
}
