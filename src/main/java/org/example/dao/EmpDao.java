package org.example.dao;

import org.example.entities.Account;
import org.example.entities.Log;

import java.util.List;

public interface EmpDao {
        public void login(String username, String password);
        public List<Account> viewAccount(int userId);
        public List<Log> viewLog();
        public void appAccount(int accountId, int customerId);
        public void rejAccount(int accountId, int customerId);
}
