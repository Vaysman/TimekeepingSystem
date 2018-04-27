package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.timekeeping.data.EmployeeAuthorizationData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringJoiner;

public class AuthorizationPage extends Page {

    private ModelFacade modelFacade;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public AuthorizationPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        super(modelFacade, dataInputStream, dataOutputStream);
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        try {
            pageLogic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pageLogic() throws IOException {
        Employee employee = logIn(dataInputStream);
        Page page;
        if (employee != null) {
            String employeeInfo = getInformation(employee);
            dataOutputStream.writeUTF(employeeInfo);
            String status = employee.getEmployeeStatusEnum().toString();
            dataOutputStream.writeUTF(status);
            switch (status) {
                case "EMPLOYEE": {
                    page = new EmployeePage(modelFacade, dataInputStream, dataOutputStream);
                    break;
                }
                case "SUPERVISOR": {
                    page = new SupervisorPage(modelFacade, dataInputStream, dataOutputStream);
                    break;
                }
                case "TIMEKEEPER": {
                    page = new TimekeeperPage(modelFacade, dataInputStream, dataOutputStream);
                    break;
                }
                default: {
                    //
                }
            }
        }
        else {
            dataOutputStream.writeUTF("NOT_EXIST");
        }
    }

    private Employee logIn(DataInputStream dataInputStream) {
        String login = null;
        String password = null;
        try {
            login = dataInputStream.readUTF();
            password = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelFacade = new ModelFacade(new EmployeeAuthorizationData(login, password));
        return modelFacade.getEmployee();
    }

    private String getInformation(Employee employee) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(employee.getName());
        stringJoiner.add(employee.getSurname());
        stringJoiner.add(employee.getTelephoneNumber());
        stringJoiner.add(employee.getEmployeeStatusEnum().toString());
        stringJoiner.add(employee.getEmployeeAuthorizationData().getLogin());
        stringJoiner.add(employee.getEmployeeAuthorizationData().getPassword());
        stringJoiner.add(employee.getDepartment().getDepartmentName());
        stringJoiner.add(employee.getDepartment().getBranchOffice().getBranchOfficeName());
        return stringJoiner.toString();
    }
}
