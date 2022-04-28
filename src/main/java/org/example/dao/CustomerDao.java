package org.example.dao;

import org.example.entities.Account;
import org.example.entities.Customer;

public interface CustomerDao {
    public void register (Customer user);
    public int login (String username, String password);
    public void apply(int accountId, String type, double balance);
    public Account getBalance(int accountId, int customerId);
    public void withdraw (int customerId, int accountId, double amount);
    public void deposit (int customerId, int accountId, double amount);
    public void postTransfer (int customerId, int id, int toId, double amount);
    public void acceptTransfer (int customerId, int id);
    public void initTables();
    public void fillTables();
}
