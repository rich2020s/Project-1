package dao;

import dataStructure.CustomArrayList;
import entities.Tickets;

import java.sql.SQLException;

public interface ManagerDao {
    public CustomArrayList<Tickets> viewAllPendingTickets();
    public CustomArrayList<Tickets> viewAllTickets();
    public boolean acceptTicket(int id);
    public boolean denyTicket(int id);
    public void initTables() throws SQLException;
    public void insertRequest(Tickets ticket);
    public Tickets getTicketsById(int id);
    public void insertFakeEmployee();
    public void getEmployee();
}
