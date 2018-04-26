package ru.wkn.server.model.pages;

import ru.wkn.server.model.ModelFacade;

public abstract class Page {

    private ModelFacade modelFacade;

    public Page(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public ModelFacade getModelFacade() {
        return modelFacade;
    }
}
