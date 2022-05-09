package org.example.dao;

import org.example.entities.Tickets;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDao {
//        public void login(String username, String password);
//        public List<Account> viewAccount(int userId);
        public List<Tickets> viewLog();
        public void appAccount(int accountId, int customerId);
        public void rejAccount(int accountId, int customerId);

    void initTables() throws SQLException;
    int insertRequest() throws SQLException;
}
