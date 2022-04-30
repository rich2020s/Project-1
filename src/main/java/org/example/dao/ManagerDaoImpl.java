package org.example.dao;

<<<<<<< HEAD
=======
import org.example.ConnectionFactory;
import org.example.entities.Tickets;

>>>>>>> Dae02
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import org.example.ConnectionFactory;

public class ManagerDaoImpl implements ManagerDao{
    Connection connection;

    public ManagerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }
    @Override
    public void viewAllPendingRequests() {
        String sql = "SELECT * FROM tickets WHERE state = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "pending");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

            }

        } catch(SQLException e) {

        }
    }

    @Override
    public void viewAllTickets() {
        String sql = "SELECT * FROM tickets";

    }

    @Override
    public void acceptRequest() {
        String sql = "UPDATE tickets SET state = ? WHERE id = ?";
    }

    @Override
    public void denyRequest() {
        String sql = "UPDATE tickets SET state = ? WHERE id = ?";
    }
}
=======
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    Connection connection;
    // When we instantiate, we get this connection.
    public ManagerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }


    @Override
    public void login(String username, String password) {
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String usernameData = resultSet.getString("username");
                String passwordData = resultSet.getString("password");
                Manager employee = new Manager(id, usernameData, passwordData);
                System.out.println("Login successful!");
            }
            else {
                System.out.println("Employee login failed.");
                System.exit(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> viewAccount(int customerId) {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE customerid =?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerid = resultSet.getInt("customerid");
                String type = resultSet.getString("type");
                int balance = resultSet.getInt("balance");
                String status = resultSet.getString("status");
                Account account = new Account(id,customerid,type,balance,status);
                accountList.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (accountList.size() > 0){
            System.out.println("Retrieved account!");
        }
        else {
            System.out.println("No account associated with this customer id.");
            System.exit(0);
        }
        return accountList;
    }

    @Override
    public List<Tickets> viewLog() {
        List<Tickets> logList = new ArrayList<>();
        String sql = "SELECT * FROM log;";

        try {
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedstatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                int customerid = resultSet.getInt("customerid");
                int accountid = resultSet.getInt("accountid");
                String description = resultSet.getString("description");
                double amount = resultSet.getInt("amount");
                Tickets log = new Tickets(id, date, customerid,accountid, description,amount);
                logList.add(log);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }

    @Override
    public void appAccount(int accountId, int customerId) {
        String sql = "UPDATE account SET status = 'approved' WHERE id = ? AND customerid = ?;";
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
        String sql = "UPDATE account SET status = 'denied' WHERE id = ? AND customerid = ?;";
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
}

>>>>>>> Dae02
