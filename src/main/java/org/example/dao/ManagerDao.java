package org.example.dao;

import org.example.dataStructure.CustomArrayList;
import org.example.entities.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ManagerDao {
    public CustomArrayList<Ticket> viewAllPendingRequests();
    public CustomArrayList<Ticket> viewAllTickets();
    public int acceptRequest(int id);
    public int denyRequest(int id);
    public void initTables() throws SQLException;
    public int insertRequest() throws SQLException;
}
