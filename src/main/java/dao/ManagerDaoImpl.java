package dao;

import org.example.ConnectionFactory;
import entities.Tickets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    Connection connection;
    // When we instantiate, we get this connection.
    public ManagerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

//
//    @Override
//    public void login(String username, String password) {
//        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String usernameData = resultSet.getString("username");
//                String passwordData = resultSet.getString("password");
//                Manager employee = new Manager(id, usernameData, passwordData);
//                System.out.println("Login successful!");
//            }
//            else {
//                System.out.println("Employee login failed.");
//                System.exit(0);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<Account> viewAccount(int customerId) {
//        List<Account> accountList = new ArrayList<>();
//        String sql = "SELECT * FROM account WHERE customerid =?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, customerId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//                int id = resultSet.getInt("id");
//                int customerid = resultSet.getInt("customerid");
//                String type = resultSet.getString("type");
//                int balance = resultSet.getInt("balance");
//                String status = resultSet.getString("status");
//                Account account = new Account(id,customerid,type,balance,status);
//                accountList.add(account);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (accountList.size() > 0){
//            System.out.println("Retrieved account!");
//        }
//        else {
//            System.out.println("No account associated with this customer id.");
//            System.exit(0);
//        }
//        return accountList;
//    }

    @Override
    public List<Tickets> viewLog() {
        List<Tickets> logList = new ArrayList<>();
        String sql = "SELECT * FROM log;";

        try {
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedstatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                Timestamp created_at = resultSet.getTimestamp("created_at");
                int customerid = resultSet.getInt("customerid");
                int accountid = resultSet.getInt("accountid");
                String description = resultSet.getString("description");
                String state = resultSet.getString("state");
                Tickets log = new Tickets(id, created_at, customerid,accountid, description, state);
                logList.add(log);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }

    @Override
    public void appAccount(int accountId, int customerId) {
        String sql = "UPDATE accounts SET status = 'approved' WHERE id = ? AND customerid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accountId);
            preparedStatement.setInt(2,customerId);
            int count = preparedStatement.executeUpdate();

            if (count==1) {
                System.out.println("Account approved!");
            }
            else {
                System.out.println("Something went wrong with approval.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void rejAccount(int accountId, int customerId) {
        String sql = "UPDATE accounts SET status = 'denied' WHERE id = ? AND customerid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accountId);
            preparedStatement.setInt(2,customerId);
            int count = preparedStatement.executeUpdate();

            if (count==1) {
                System.out.println("Account rejected!");
            }
            else {
                System.out.println("Something went wrong with denial.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initTables () throws SQLException {
        String accountsQuery = "CREATE TABLE accounts (id SERIAL PRIMARY KEY, username VARCHAR(50), password VARCHAR (50)," +
                " user_type VARCHAR(1));";
        String ticketsQuery = "CREATE TABLE tickets (id SERIAL PRIMARY KEY, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, user_id integer REFERENCES accounts(id), price decimal(10,2), description VARCHAR(100), state VARCHAR(50));";
        try {
            connection.setAutoCommit(false);
            PreparedStatement prepareAccountsTable = connection.prepareStatement(accountsQuery);
            PreparedStatement prepareTicketsTable = connection.prepareStatement(ticketsQuery);
            prepareAccountsTable.execute();
            prepareTicketsTable.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            System.out.println("Transaction is being rollback.");
        }
    }
    @Override
    public int insertRequest() throws SQLException {
        String sql = "INSERT INTO tickets (id, created_at, user_id, price, description, state) VALUES(default, default, 1, 3.14, 'last Friday', 'pending')";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
            } else {
                System.out.println("no data.");
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

