package org.example;

import org.example.dao.EmployeeDao;
import org.example.dao.DaoFactory;
import org.example.entities.Accounts;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DaoTest {
    private EmployeeDao customerDao;

    @Before
    public void initTables() {
        customerDao = DaoFactory.getCustomerDao();
        customerDao.initTables();
    }

    @Test
    public void testLogin() {
        customerDao.fillTables();
        int id = customerDao.login("cust1", "1");
        assertEquals(1,id);

    }

    @Test
    public void testRegistration() {
        Accounts customer = new Accounts("cust1", "1");
        customerDao.register(customer);
        int id = customerDao.login(customer.getUsername(), customer.getPassword());
        assertEquals(1,id);
    }


}
