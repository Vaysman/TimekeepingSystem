package ru.wkn.server.pages;

import ru.wkn.server.pages.container.Container;
import ru.wkn.server.model.ModelFacade;
import ru.wkn.server.model.branchoffice.department.employee.Employee;
import ru.wkn.server.model.datasource.dao.persistent.PersistentException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EmployeeManagerPage extends Page {

    private ModelFacade modelFacade;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public EmployeeManagerPage(ModelFacade modelFacade, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        super(modelFacade, dataInputStream, dataOutputStream);
        this.modelFacade = modelFacade;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        try {
            pageLogic();
        } catch (IOException | PersistentException e) {
            e.printStackTrace();
        }
    }

    private void pageLogic() throws IOException, PersistentException {
        Page page;
        String action;
        do {
            action = dataInputStream.readUTF();
            switch (action) {
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
                        dataOutputStream.writeUTF(Container.readEmployeeInformation(modelFacade.getEmployeeManager().readEmployee(employeeID)));
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
                    try {
                        sendEmployeesInformation();
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "EXIT": {
                    page = new SupervisorPage(modelFacade, dataInputStream, dataOutputStream);
                    break;
                }
                default: {
                    throw new PersistentException("COMMAND_NOT_EXIST");
                }
            }
        } while (!action.equals("EXIT"));
    }

    private void sendEmployeesInformation() throws PersistentException, IOException {
        String employees = "";
        int size = modelFacade.getEmployeeManager().getAll().size();
        for (int i = 0; i < size; i++) {
            employees.concat("\n" + Container.readEmployeeInformation(modelFacade.getEmployeeManager().readEmployee(i)));
        }
        dataOutputStream.writeUTF(employees);
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
