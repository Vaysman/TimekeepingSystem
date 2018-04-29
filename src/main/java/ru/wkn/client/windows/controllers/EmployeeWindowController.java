package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.wkn.client.windows.AuthorizationWindow;
import ru.wkn.client.windows.TimekeepingEventManagerWindow;
import ru.wkn.client.windows.Window;
import ru.wkn.client.windows.container.Container;

import java.io.IOException;

public class EmployeeWindowController {

    @FXML
    public AnchorPane employee;
    @FXML
    public Button aboutMeButton;
    @FXML
    public Button createEventButton;
    @FXML
    public Button myTasksButton;
    @FXML
    public Button searchEmployeeButton;
    @FXML
    public Button calendarEventsButton;
    @FXML
    public Button exitButton;

    private void hide() {
        employee.getScene().getWindow().hide();
    }

    @FXML
    public void aboutMeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("INFO");
            Information.writeMessage("Информация о пользователе", Container.getDataInputStream().readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("EXIT");
            hide();
            Window window = new AuthorizationWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createEventClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("EVENT_MANAGER");
            hide();
            Window window = new TimekeepingEventManagerWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void myTasksClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("MY_TASKS");
            hide();
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("READ");
            hide();
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void calendarEventsClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("CALENDAR_EVENTS");
            hide();
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
