package org.example;

import org.example.dao.DaoFactory;
import org.example.dao.ManagerDao;
import org.example.dataStructure.CustomArrayList;
import org.example.entities.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DaoTest {
//    private EmployeeDao customerDao;
        private ManagerDao managerDao;
    @Before
    public void initTables() {
        managerDao = DaoFactory.getManagerDao();
        try {
            managerDao.initTables();
            managerDao.insertRequest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertTickets() {
        try{
            int id = managerDao.insertRequest();
            if (id == 0) {
                System.out.println("update failed");
            }
            assertEquals(2,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testViewAllPendingRequest() {
        CustomArrayList<Ticket> tickets = managerDao.viewAllPendingRequests();
        assertEquals(1, tickets.get(0).getId());
        assertEquals(3.14, tickets.get(0).getAmount(), 0);
        assertEquals("pending", tickets.get(0).getState());
        assertEquals("last Friday", tickets.get(0).getDescription());

    }

    @Test
    public void testViewAllTickets() {
        CustomArrayList<Ticket> tickets = managerDao.viewAllTickets();
        if (tickets != null) {
//            assertEquals(1, tickets.get(0));
        }
    }

    @Test
    public void testAcceptTicket() {
        int rs = managerDao.acceptRequest(1);
        assertEquals(1, rs);
//        CustomArrayList<Ticket> tickets = managerDao.();
//        assertEquals("approved", tickets.get(0).getState());
    }
//    @Test
//    public void testLogin() {
//        customerDao.fillTables();
//        int id = customerDao.login("cust1", "1");
//        assertEquals(1,id);
//
//    }
//
//    @Test
//    public void testRegistration() {
//        Accounts customer = new Accounts("cust1", "1");
//        customerDao.register(customer);
//        int id = customerDao.login(customer.getUsername(), customer.getPassword());
//        assertEquals(1,id);
//    }


}
