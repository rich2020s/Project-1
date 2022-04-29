package org.example.dao;

public interface ManagerDao {
    public void viewAllPendingRequests();
    public void viewAllTickets();
    public void acceptRequest();
    public void denyRequest();
}
