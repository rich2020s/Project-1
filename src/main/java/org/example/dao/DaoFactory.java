package org.example.dao;

public class DaoFactory {
    private static EmployeeDao employeeDao;
    private static ManagerDao managerDao;
    private DaoFactory(){
    }

    public static EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    public static ManagerDao getManagerDao() {
        if (managerDao == null) {
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }
}
