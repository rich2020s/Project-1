package dao;

import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import entities.Accounts;
import entities.Tickets;

import java.sql.SQLException;

public interface EmployeeDao {
    public void register (Accounts employee);
    public Accounts login (Accounts account);
    public void apply(Tickets ticket);
    public CustomArrayList<Tickets> getPast(Accounts account);
    public CustomArrayList<Tickets> getPending(Accounts account);
    public CustomArrayList<Tickets> getHistory(Accounts account);

    public void initTables();
    public void fillTickets();
    public void dropTables();
}
