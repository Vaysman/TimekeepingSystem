package ru.wkn.server.pages;

import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringJoiner;

public class EmployeeManagerPage extends Page {

    private ModelFacade modelFacade;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public EmployeeManagerPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        super(modelFacade, dataInputStream, dataOutputStream);
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        pageLogic();
    }

    private void pageLogic() throws IOException {
        while (!dataInputStream.readUTF().equals("EXIT")) {
            switch (dataInputStream.readUTF()) {
                case "CREATE": {
                    try {
                        createEmployee();
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "DELETE": {
                    try {
                        deleteEmployee(modelFacade.getSearcher().getEmployeeByID(dataInputStream.readInt()));
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "READ": {
                    int employeeID = dataInputStream.readInt();
                    try {
                        dataOutputStream.writeUTF(readEmployeeInformation(modelFacade.getEmployeeManager().readEmployee(employeeID)));
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "UPDATE": {
                    try {
                        update();
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "DELETE_ALL": {
                    try {
                        modelFacade.getEmployeeManager().deleteAll();
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "GET_ALL": {
                    //
                }
            }
        }
    }

    private void getEmployeesInformation() throws PersistentException, IOException {
        String employees = "";
        for (int i = 0; i < modelFacade.getEmployeeManager().getAll().size(); i++) {
            employees.concat("\n" + readEmployeeInformation(modelFacade.getEmployeeManager().readEmployee(i)));
        }
        dataOutputStream.writeUTF(employees);
    }

    private String readEmployeeInformation(Employee employee) {
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

    private void createEmployee() throws PersistentException, IOException {
        String name = dataInputStream.readUTF();
        String surname = dataInputStream.readUTF();
        String telephoneNumber = dataInputStream.readUTF();
        String employeeStatus = dataInputStream.readUTF();
        String login = dataInputStream.readUTF();
        String password = dataInputStream.readUTF();
        String department = dataInputStream.readUTF();
        String branchOffice = dataInputStream.readUTF();
        modelFacade.getEmployeeManager().createEmployee(name, surname, telephoneNumber, employeeStatus, login, password, department, branchOffice, null);
    }

    private void deleteEmployee(Employee persistentEmployee) throws PersistentException {
        modelFacade.getEmployeeManager().deleteEmployee(persistentEmployee);
    }

    private void update() throws PersistentException, IOException {
        int employeeID = dataInputStream.readInt();
        String name = dataInputStream.readUTF();
        String surname = dataInputStream.readUTF();
        String telephoneNumber = dataInputStream.readUTF();
        String employeeStatus = dataInputStream.readUTF();
        String login = dataInputStream.readUTF();
        String password = dataInputStream.readUTF();
        String department = dataInputStream.readUTF();
        String branchOffice = dataInputStream.readUTF();
        modelFacade.getEmployeeManager().updateEmployee(new Employee(employeeID, name, surname, telephoneNumber, employeeStatus, login, password, department, branchOffice, null));
    }
}
