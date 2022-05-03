package org.example;

import org.example.dao.DaoFactory;
import org.example.dao.ManagerDao;
import org.example.dataStructure.CustomArrayList;
import org.example.entities.Tickets;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DaoTest {
//    private EmployeeDao customerDao;
        private static ManagerDao managerDao;
    @BeforeClass
    public static void initTables() {
        managerDao = DaoFactory.getManagerDao();
        try {
            managerDao.initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void insertEmployee(){
        managerDao.insertFakeEmployee();
    }
    @Before
    public void insertTickets() {
        Tickets ticket = new Tickets(1, 3.14, "black coffee");
        try {
            managerDao.insertRequest(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFakeEmployee() {
        managerDao.insertFakeEmployee();
        managerDao.getEmployee();
    }

    @Test
    public void testInsertTickets() {
        Tickets ticket = new Tickets(1, 3.14, "black coffee");
        try{
            managerDao.insertRequest(ticket);
            Tickets ticketData = managerDao.getTicketsById(1);
            assertEquals(3.14, ticketData.getPrice(), 0);
            assertEquals("black coffee", ticketData.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testViewAllPendingRequest() {
        CustomArrayList<Tickets> tickets = managerDao.viewAllPendingRequests();
        assertEquals(2, tickets.get(0).getId());
        assertEquals(3.14, tickets.get(0).getPrice(), 0);
        assertEquals("pending", tickets.get(0).getState());
        assertEquals("black coffee", tickets.get(0).getDescription());

    }

//    @Test
//    public void testViewAllTickets() {
//        CustomArrayList<Tickets> tickets = managerDao.viewAllTickets();
//
////        if (tickets != null) {
//            assertEquals(1, tickets.get(0).getId());
//            assertEquals(3.14, tickets.get(0).getPrice(), 0);
////        }
//    }

    @Test
    public void testAcceptTicket() {
        managerDao.acceptRequest(1);
        Tickets ticket = managerDao.getTicketsById(1);

//        assertEquals(1, rs);
//        CustomArrayList<Ticket> tickets = managerDao.();
        assertEquals("approved", ticket.getState());
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
