package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.wkn.client.windows.AuthorizationWindow;
import ru.wkn.client.windows.TimekeepingEventManagerWindow;
import ru.wkn.client.windows.Window;
import ru.wkn.client.windows.container.Container;

import java.io.IOException;

public class EmployeeWindowController {

    @FXML
    public AnchorPane employee;
    @FXML
    public GridPane creatorEventGridPane;
    @FXML
    public Label aboutMeLabel;
    @FXML
    public RadioButton aboutMeButton;
    @FXML
    public RadioButton createEventButton;
    @FXML
    public RadioButton myTasksButton;
    @FXML
    public RadioButton searchEmployeeButton;
    @FXML
    public RadioButton calendarEventsButton;
    @FXML
    public Button exitButton;
    public Button startShiftButton;
    public Button startBreakButton;
    public Button endBreakButton;
    public Button endShiftButton;

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

    public void startShiftClick(ActionEvent actionEvent) {
        //
    }

    public void startBreakClick(ActionEvent actionEvent) {
        //
    }

    public void endBreakClick(ActionEvent actionEvent) {
        //
    }

    public void endShiftClick(ActionEvent actionEvent) {
        //
    }
}
