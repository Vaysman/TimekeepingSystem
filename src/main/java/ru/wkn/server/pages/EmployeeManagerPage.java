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
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

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
        String action;
        Page page;
        do {
            synchronized (dataInputStream) {
                action = dataInputStream.readUTF();
            }
            switch (action) {
                case "CREATE": {
                    createEmployee();
                    break;
                }
                case "DELETE": {
                    deleteEmployee(modelFacade.getSearcher().getEmployeeByID(dataInputStream.readInt()));
                    break;
                }
                case "READ": {
                    readEmployee();
                    break;
                }
                case "UPDATE": {
                    update();
                    break;
                }
                case "DELETE_ALL": {
                    deleteAllEmployees();
                    break;
                }
                case "GET_ALL": {
                    sendEmployeesInformation();
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

    private synchronized void sendEmployeesInformation() throws PersistentException, IOException {
        String employees = "";
        int size = modelFacade.getEmployeeManager().getAll().size();
        for (int i = 0; i < size; i++) {
            employees.concat("\n" + Container.readEmployeeInformation(modelFacade.getEmployeeManager().readEmployee(i)));
        }
        dataOutputStream.writeUTF(employees);
    }

    private synchronized void createEmployee() throws PersistentException, IOException {
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

    private synchronized void readEmployee() throws IOException, PersistentException {
        int employeeID = dataInputStream.readInt();
        dataOutputStream.writeUTF(Container.readEmployeeInformation(modelFacade.getEmployeeManager().readEmployee(employeeID)));
    }

    private synchronized void deleteEmployee(Employee persistentEmployee) throws PersistentException {
        modelFacade.getEmployeeManager().deleteEmployee(persistentEmployee);
    }

    private synchronized void deleteAllEmployees() throws PersistentException {
        modelFacade.getEmployeeManager().deleteAll();
    }

    private synchronized void update() throws PersistentException, IOException {
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
