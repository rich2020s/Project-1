package dao;

import dataStructure.CustomArrayList;
import entities.Tickets;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class managerDaoTest {
    private static ManagerDao managerDao;
    @Before
    public void initTables() {
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
        managerDao.insertRequest(ticket);
    }
    @Test
    public void testFakeEmployee() {
//        Tickets ticket = new Tickets(1, 4, "candy");
//        managerDao.insertFakeEmployee();
//        managerDao.insertFakeEmployee();
        managerDao.dropTable();
        managerDao.insertFakeEmployee();
//        managerDao.getEmployee();
    }
    @Test
    public void testInsertTickets() {
        Tickets ticket = new Tickets(1, 100, "transportation");
        managerDao.insertRequest(ticket);
        Tickets ticketData = managerDao.getTicketsById(2);
        assertEquals(100, ticketData.getPrice(), 0);
        assertEquals("transportation", ticketData.getDescription());
        managerDao.dropTable();
        Tickets ticket2 = new Tickets(1,100, "test");
        managerDao.insertRequest(ticket);
    }

    @Test
    public void testViewAllPendingRequest() {
        Tickets ticket1 = new Tickets(1, 125, "groceries");
        Tickets ticket2 = new Tickets(1, 4, "candy");
        managerDao.insertRequest(ticket1);
        managerDao.insertRequest(ticket2);

        CustomArrayList<Tickets> tickets = managerDao.viewAllPendingTickets();
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i).getId());
        }
        assertEquals(2, tickets.get(1).getId());
        assertEquals(125, tickets.get(1).getPrice(), 0);
        assertEquals("pending", tickets.get(1).getState());
        assertEquals("groceries", tickets.get(1).getDescription());

        assertEquals(3, tickets.get(2).getId());
        assertEquals(4, tickets.get(2).getPrice(), 0);
        assertEquals("pending", tickets.get(2).getState());
        assertEquals("candy", tickets.get(2).getDescription());
        managerDao.dropTable();
        managerDao.viewAllPendingTickets();
    }

    @Test
    public void testViewAllTickets() {
        Tickets ticket1 = new Tickets(1, 125, "groceries");
        Tickets ticket2 = new Tickets(1, 4, "candy");
        managerDao.insertRequest(ticket1);
        managerDao.insertRequest(ticket2);

        CustomArrayList<Tickets> tickets = managerDao.viewAllPendingTickets();
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i).getId());
        }
        assertEquals(1, tickets.get(0).getId());
        assertEquals(3.14, tickets.get(0).getPrice(), 0);
        assertEquals("pending", tickets.get(0).getState());
        assertEquals("black coffee", tickets.get(0).getDescription());

        assertEquals(2, tickets.get(1).getId());
        assertEquals(125, tickets.get(1).getPrice(), 0);
        assertEquals("pending", tickets.get(1).getState());
        assertEquals("groceries", tickets.get(1).getDescription());

        assertEquals(3, tickets.get(2).getId());
        assertEquals(4, tickets.get(2).getPrice(), 0);
        assertEquals("pending", tickets.get(2).getState());
        assertEquals("candy", tickets.get(2).getDescription());
        managerDao.dropTable();
        managerDao.viewAllTickets();
    }

    @Test
    public void testDenyTicket() {
        managerDao.denyTicket(1);
        Tickets ticket = managerDao.getTicketsById(1);
        assertEquals("denied", ticket.getState());
        boolean success = managerDao.denyTicket(1);
        assertEquals(false, success);
    }
    @Test
    public void testAcceptTicket() {
        managerDao.acceptTicket(1);
        Tickets ticket = managerDao.getTicketsById(1);
        assertEquals("approved", ticket.getState());
        boolean success = managerDao.acceptTicket(1);
        assertEquals(false, success);
    }
    @Test
    public void testSelectingTicketById() {
        Tickets ticket = managerDao.getTicketsById(0);
    }
}
