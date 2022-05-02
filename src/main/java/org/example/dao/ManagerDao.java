package org.example.dao;

import org.example.dataStructure.CustomArrayList;
import org.example.entities.Tickets;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ManagerDao {
    public CustomArrayList<Tickets> viewAllPendingRequests();
    public CustomArrayList<Tickets> viewAllTickets();
    public void acceptRequest(int id);
    public void denyRequest(int id);
    public void initTables() throws SQLException;
    public void insertRequest(Tickets ticket) throws SQLException;
    public Tickets getTicketsById(int id);
}
