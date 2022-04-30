package org.example.dao;

import org.example.entities.Accounts;
import org.example.entities.Tickets;

import java.util.List;

public interface EmployeeDao {
    public void register (Accounts employee);
    public int login (String username, String password);
    public void apply(int user_id, double price, String description);
    public List<Tickets> getPast(int user_id);
    public List<Tickets> getPending(int user_id);
    public List<Tickets> getHistory(int user_id);

    public void initTables();
    public void fillTables();
}
