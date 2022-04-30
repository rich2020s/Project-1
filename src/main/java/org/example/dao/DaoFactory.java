package org.example.dao;

public class DaoFactory {
<<<<<<< HEAD
    private static CustomerDao customerDao;
    private static EmpDao empDao;
=======
    private static EmployeeDao employeeDao;
>>>>>>> Dae02
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
    public static ManagerDao getManagerDao() {
        if (managerDao == null) {
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }
}
