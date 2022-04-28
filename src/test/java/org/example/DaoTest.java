package org.example;

import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.example.entities.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DaoTest {
    private CustomerDao customerDao;

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
        Customer customer = new Customer("cust1", "1");
        customerDao.register(customer);
        int id = customerDao.login(customer.getUsername(), customer.getPassword());
        assertEquals(1,id);
    }


}
