package ru.wkn.server.model.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;

public class AuthorizationPage extends Page {

    private String login;
    private String password;

    private AuthorizationPage(ModelFacade modelFacade) {
        super(modelFacade);
    }

    public AuthorizationPage(String login, String password) {
        this(new ModelFacade(new EmployeeAuthorizationData(login, password)));
        this.login = login;
        this.password = password;
    }
}
