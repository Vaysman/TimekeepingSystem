package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.wkn.client.windows.SupervisorWindow;
import ru.wkn.client.windows.Window;
import ru.wkn.client.windows.container.Container;

import java.io.IOException;

public class EmployeeManagerWindowController {

    @FXML
    public AnchorPane employeeManager;
    @FXML
    public GridPane creatorGridPane;
    @FXML
    public GridPane deleteGridPane;
    @FXML
    public GridPane updateGridPane;
    @FXML
    public RadioButton searchEmployeeButton;
    @FXML
    public RadioButton editEmployeeButton;
    @FXML
    public RadioButton deleteEmployeeButton;
    @FXML
    public Button exitButton;
    @FXML
    public RadioButton createEmployeeButton;
    @FXML
    public RadioButton allEmployeeButton;
    @FXML
    public TextField deleteTextField;
    @FXML
    public Button deleteButton;
    @FXML
    public RadioButton deleteAllEmployeesButton;
    @FXML
    public TextField branchOfficeTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField departmentTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField loginTextField;
    @FXML
    public TextField telephoneNumberTextField;
    @FXML
    public TextField surnameTextField;
    @FXML
    public RadioButton supervisorButton;
    @FXML
    public RadioButton timekeeperButton;
    @FXML
    public RadioButton employeeButton;
    @FXML
    public Button createButton;
    @FXML
    public TextField newBranchOfficeTextField;
    @FXML
    public TextField newNameTextField;
    @FXML
    public TextField newDepartmentTextField;
    @FXML
    public TextField newPasswordTextField;
    @FXML
    public TextField newLoginTextField;
    @FXML
    public TextField newTelephoneNumberTextField;
    @FXML
    public TextField newSurnameTextField;
    @FXML
    public RadioButton newSupervisorButton;
    @FXML
    public RadioButton newTimekeeperButton;
    @FXML
    public RadioButton newEmployeeButton;
    @FXML
    public Button editButton;
    @FXML
    public TextField currentIDTextField;
    @FXML
    public Button deleteAllButton;

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

    public void allEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("GET_ALL");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteClick(ActionEvent actionEvent) {
        //
    }

    public void deleteAllEmployeesClick(ActionEvent actionEvent) {
        //
    }

    public void editClick(ActionEvent actionEvent) {
        //
    }
}
