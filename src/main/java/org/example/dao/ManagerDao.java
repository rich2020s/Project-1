package org.example.dao;

<<<<<<< HEAD
public interface ManagerDao {
    public void viewAllPendingRequests();
    public void viewAllTickets();
    public void acceptRequest();
    public void denyRequest();
=======
import org.example.entities.Tickets;

import java.util.List;

public interface ManagerDao {
        public void login(String username, String password);
        public List<Account> viewAccount(int userId);
        public List<Tickets> viewLog();
        public void appAccount(int accountId, int customerId);
        public void rejAccount(int accountId, int customerId);
>>>>>>> Dae02
}
