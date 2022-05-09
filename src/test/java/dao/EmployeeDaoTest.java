package dao;

import dataStructure.CustomList;
import entities.Accounts;
import entities.Tickets;
import org.h2.jdbc.JdbcSQLException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeDaoTest {
    private EmployeeDao employeeDao;
    Accounts test = new Accounts("empTest","test");


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.dropTables();
        employeeDao.initTables();
        employeeDao.fillTickets();
        employeeDao.register(test);
    }

    @Test
    public void testRegister() {
        Accounts newTest = new Accounts("empTest2","2");
        employeeDao.register(newTest);
        //Then I will log in with this new credential.
        Accounts result = employeeDao.login(newTest);
        assertEquals("empTest2", result.getUsername());
        assertEquals("2", result.getPassword());
    }

    @Test
    public void testRegisterSQLException() throws SQLException{
        Accounts testExc = new Accounts("empTest", "test");
        employeeDao.register(testExc);

//        assertThrows(SQLException., () -> failing test);
//        Exception exception = assertThrows(Exception.class, () -> employeeDao.register(testExc));
//        assertEquals("Something went wrong adding the new employee account.", exception.getMessage());
    }

    @Test
    public void testLogin()  {
        Accounts result = employeeDao.login(test);
        assertEquals("empTest", result.getUsername());
        assertEquals("test", result.getPassword());
    }

    @Test
    public void testLoginSQLException() throws SQLException{
        Accounts testExc = new Accounts("empTest", "test2");
        employeeDao.login(testExc);
    }

    @Test
    public void testGetPast()  {
        Accounts currentUser = employeeDao.login(test);
        CustomList<Tickets> pastTickets = employeeDao.getPast(currentUser);
        Tickets testTicketA = pastTickets.get(0);
        assertEquals(1,testTicketA.getUser_id());
        assertEquals(3.14, testTicketA.getPrice(), 0.01);
        assertEquals("last Friday", testTicketA.getDescription());
        assertEquals("approved", testTicketA.getState());

        Tickets testTicketD = pastTickets.get(1);
        assertEquals(1,testTicketD.getUser_id());
        assertEquals(3.14, testTicketD.getPrice(), 0.01);
        assertEquals("last Friday", testTicketD.getDescription());
        assertEquals("denied", testTicketD.getState());

    }

    @Test
    public void testGetPastSQLException() throws SQLException{
        Accounts falseUser = new Accounts(0, "Fake", "Fake", "E");
        employeeDao.dropTables();
        CustomList<Tickets> pastTickets = employeeDao.getPast(falseUser);
    }

    @Test
    public void testGetPending()  {

        employeeDao.register(test);
        Accounts currentUser = employeeDao.login(test);
        CustomList<Tickets> pendingTickets = employeeDao.getPending(currentUser);
        Tickets testTicket = pendingTickets.get(0);
        assertEquals(1,testTicket.getUser_id());
        assertEquals(3.14, testTicket.getPrice(), 0.01);
        assertEquals("last Friday", testTicket.getDescription());
        assertEquals("pending", testTicket.getState());

    }

    @Test
    public void testGetPendingSQLException() throws SQLException{
        Accounts falseUser = new Accounts(-10, "Fake", "Fake", "E");
        employeeDao.dropTables();
        CustomList<Tickets> pastTickets = employeeDao.getPending(falseUser);
    }

    @Test
    public void testGetAll()  {

        employeeDao.register(test);
        Accounts currentUser = employeeDao.login(test);
        CustomList<Tickets> allTickets = employeeDao.getHistory(currentUser);
        Tickets testTicketA = allTickets.get(0);
        assertEquals(1,testTicketA.getUser_id());
        assertEquals(3.14, testTicketA.getPrice(), 0.01);
        assertEquals("last Friday", testTicketA.getDescription());
        assertEquals("pending", testTicketA.getState());

        Tickets testTicketD = allTickets.get(1);
        assertEquals(1,testTicketD.getUser_id());
        assertEquals(3.14, testTicketD.getPrice(), 0.01);
        assertEquals("last Friday", testTicketD.getDescription());
        assertEquals("approved", testTicketD.getState());

        Tickets testTicketP = allTickets.get(2);
        assertEquals(1,testTicketP.getUser_id());
        assertEquals(3.14, testTicketP.getPrice(), 0.01);
        assertEquals("last Friday", testTicketP.getDescription());
        assertEquals("denied", testTicketP.getState());

    }
    @Test
    public void testGetAllSQLException() throws SQLException{
        Accounts falseUser = new Accounts(-10, "Fake", "Fake", "E");
        employeeDao.dropTables();
        CustomList<Tickets> pastTickets = employeeDao.getHistory(falseUser);
    }

    @Test
    public void testApply() {

        employeeDao.register(test);
        Accounts currentUser = employeeDao.login(test);
        Tickets newTicket = new Tickets(currentUser.getId(), 10.00, "test");
        employeeDao.apply(newTicket);
        CustomList<Tickets> pendingTickets = employeeDao.getPending(currentUser);
        Tickets testTicket = pendingTickets.get(1);
        assertEquals(1,testTicket.getUser_id());
        assertEquals(10.00, testTicket.getPrice(), 0.01);
        assertEquals("test", testTicket.getDescription());
        assertEquals("pending", testTicket.getState());
    }

    @Test
    public void testApplySQLException() throws SQLException{
        employeeDao.register(test);
        Tickets newTicket = new Tickets(10, -10.00, "test");
        employeeDao.apply(newTicket);
    }
}
