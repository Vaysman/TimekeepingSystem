package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.wkn.client.windows.SupervisorWindow;
import ru.wkn.client.windows.Window;
import ru.wkn.client.windows.container.Container;

import java.io.IOException;

public class EmployeeManagerWindowController {

    @FXML
    public AnchorPane employeeManager;
    @FXML
    public Button searchEmployeeButton;
    @FXML
    public Button editEmployeeButton;
    @FXML
    public Button deleteAllButton;
    @FXML
    public Button deleteEmployeeButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button createEmployeeButton;
    @FXML
    public Button allEmployeeButton;

    private void hide() {
        employeeManager.getScene().getWindow().hide();
    }

    @FXML
    public void searchEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("READ");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("UPDATE");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAllClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("DELETE_ALL");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("DELETE");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("EXIT");
            hide();
            Window window = new SupervisorWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("CREATE");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
