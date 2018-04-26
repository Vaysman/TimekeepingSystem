package ru.wkn.server.model.pages;

import ru.wkn.server.model.ModelFacade;

public class EmployeePage extends Page {

    private ModelFacade modelFacade;

    public EmployeePage(ModelFacade modelFacade) {
        super(modelFacade);
        this.modelFacade = modelFacade;
    }
}
