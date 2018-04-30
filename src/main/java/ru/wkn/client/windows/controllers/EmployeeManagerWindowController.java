package ru.wkn.client.windows.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private void setVisibleForGridPanes(boolean[] flag) {
        creatorGridPane.setVisible(flag[0]);
        deleteGridPane.setVisible(flag[1]);
        updateGridPane.setVisible(flag[2]);
        deleteAllButton.setVisible(flag[3]);
    }

    @FXML
    public void createEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("CREATE");
            setVisibleForGridPanes(new boolean[]{true, false, false, false});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("DELETE");
            setVisibleForGridPanes(new boolean[]{false, true, false, false});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("UPDATE");
            setVisibleForGridPanes(new boolean[]{false, false, true, false});
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void allEmployeeClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("GET_ALL");
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAllEmployeesClick(ActionEvent actionEvent) {
        setVisibleForGridPanes(new boolean[]{false, false, false, true});
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
    public void deleteAllClick(ActionEvent actionEvent) {
        try {
            Container.getDataOutputStream().writeUTF("DELETE_ALL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
        try {
            if (!deleteTextField.getText().equals("")) {
                Container.getDataOutputStream().writeUTF(deleteTextField.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editClick(ActionEvent actionEvent) {
        try {
            String status = supervisorButton.isSelected() ? "SUPERVISOR":
                    timekeeperButton.isSelected() ? "TIMEKEEPER": "EMPLOYEE";
            if ((!newNameTextField.getText().equals("")) &&(!newSurnameTextField.getText().equals("")) &&
                    (!newTelephoneNumberTextField.getText().equals("")) && (!newLoginTextField.getText().equals("")) &&
                    (!newPasswordTextField.getText().equals("")) && (!newDepartmentTextField.getText().equals("")) &&
                    (!newBranchOfficeTextField.getText().equals(""))) {
                Container.getDataOutputStream().writeUTF(currentIDTextField.getText());
                Container.getDataOutputStream().writeUTF(newNameTextField.getText());
                Container.getDataOutputStream().writeUTF(newSurnameTextField.getText());
                Container.getDataOutputStream().writeUTF(newTelephoneNumberTextField.getText());
                Container.getDataOutputStream().writeUTF(status);
                Container.getDataOutputStream().writeUTF(newLoginTextField.getText());
                Container.getDataOutputStream().writeUTF(newPasswordTextField.getText());
                Container.getDataOutputStream().writeUTF(newDepartmentTextField.getText());
                Container.getDataOutputStream().writeUTF(newBranchOfficeTextField.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createClick(ActionEvent actionEvent) {
        String status = newSupervisorButton.isSelected() ? "SUPERVISOR":
                newTimekeeperButton.isSelected() ? "TIMEKEEPER": "EMPLOYEE";
        try {
            if ((!nameTextField.getText().equals("")) &&(!surnameTextField.getText().equals("")) &&
                    (!telephoneNumberTextField.getText().equals("")) && (!loginTextField.getText().equals("")) &&
                    (!passwordTextField.getText().equals("")) &&(!departmentTextField.getText().equals("")) &&
                    (!branchOfficeTextField.getText().equals(""))) {
                Container.getDataOutputStream().writeUTF(nameTextField.getText());
                Container.getDataOutputStream().writeUTF(surnameTextField.getText());
                Container.getDataOutputStream().writeUTF(telephoneNumberTextField.getText());
                Container.getDataOutputStream().writeUTF(status);
                Container.getDataOutputStream().writeUTF(loginTextField.getText());
                Container.getDataOutputStream().writeUTF(passwordTextField.getText());
                Container.getDataOutputStream().writeUTF(departmentTextField.getText());
                Container.getDataOutputStream().writeUTF(branchOfficeTextField.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
