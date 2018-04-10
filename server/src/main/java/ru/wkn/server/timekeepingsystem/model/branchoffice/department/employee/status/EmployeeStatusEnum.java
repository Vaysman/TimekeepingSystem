package ru.wkn.server.timekeepingsystem.model.branchoffice.department.employee.status;

public enum EmployeeStatusEnum {
    EMPLOYEE {
        @Override
        public String toString() {
            return "EMPLOYEE";
        }
    },
    SUPERVISOR {
        @Override
        public String toString() {
            return "SUPERVISOR";
        }
    },
    TIMEKEEPER {
        @Override
        public String toString() {
            return "TIMEKEEPER";
        }
    }
}
