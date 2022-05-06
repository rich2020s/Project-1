package dao;

import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import entities.Accounts;
import entities.Tickets;

public interface EmployeeDao {
    public void register (Accounts employee);
    public Accounts login (Accounts account);
    public void apply(Tickets ticket);
    public CustomList<Tickets> getPast(Accounts account);
    public CustomList<Tickets> getPending(Accounts account);
    public CustomList<Tickets> getHistory(Accounts account);
}
