package org.example.dao;

public class DaoFactory {
    private static CustomerDao customerDao;
    private static EmpDao empDao;
    private static ManagerDao managerDao;
    private DaoFactory(){
    }

    public static CustomerDao getCustomerDao() {
        if (customerDao == null) {
            customerDao = new CustomerDaoImpl();
        }
        return customerDao;
    }

    public static EmpDao getEmpDao() {
        if (empDao == null) {
            empDao = new EmpDaoImpl();
        }
        return empDao;
    }
    public static ManagerDao getManagerDao() {
        if (managerDao == null) {
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }
}
