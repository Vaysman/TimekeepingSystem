package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.wkn.client.windows.*;
import ru.wkn.client.windows.container.Container;

import java.io.IOException;

public class SupervisorWindowController {

    @FXML
    public AnchorPane supervisor;
    @FXML
    public Button employeeManagerButton;
    @FXML
    public Button timekeepingReportButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button baseClientButton;

    private void hide() {
        supervisor.getScene().getWindow().hide();
    }

    @FXML
    public void employeeManagerClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("EMPLOYEE_MANAGER");
            hide();
            Window window = new EmployeeManagerWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void timekeepingReportClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("TIMEKEEPING_REPORT");
            hide();
            Window window = new TimekeepingReportWindow();
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
    public void baseClientClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("EMPLOYEE");
            hide();
            Window window = new EmployeeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
