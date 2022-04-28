package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entities.Account;
import org.example.entities.Customer;

import java.sql.*;

public class CustomerDaoImpl implements CustomerDao {

    Connection connection;
    public CustomerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void register(Customer customer) {
        String sql = "INSERT INTO customer (id, username, password) VALUES (default, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            int count = preparedStatement.executeUpdate();
            if (count ==1) {
                System.out.println("Customer account added successfully!");
            }
            else {
                System.out.println("Something when wrong adding the new customer account.");
            }

        } catch (SQLException e) {;
            e.printStackTrace();
        }

    }

    @Override
    public int login(String username, String password) {
        String sql = "SELECT * FROM customer WHERE username = ? AND password = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String usernameData = resultSet.getString("username");
                String passwordData = resultSet.getString("password");
                Customer customer = new Customer (usernameData, passwordData);
                System.out.println("Login successful!");
                return id;
            }
            else {
                System.out.println("Customer login failed.");
                System.exit(0);
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public void apply(int customerId, String type, double balance) {
        String sql = "INSERT INTO account (id, customerid, type, balance, status) VALUES (default, ?, ?, ?, 'pending');";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, balance);
            int count = preparedStatement.executeUpdate();
            if (count==1) {
                System.out.println("Bank accounted added successfully, please wait for approval.");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int accountId = resultSet.getInt(1);
                System.out.println("Generated account id is: " + accountId);
            }
            else {
                System.out.println("Something when wrong adding the new bank account.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account getBalance(int accountId, int customerId) {
        String sql = "SELECT * FROM account WHERE id = ? AND customerid=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accountId);
            preparedStatement.setInt(2,customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idData = resultSet.getInt("id");
                int customerIdData = resultSet.getInt("customerid");
                String type = resultSet.getString("type");
                int balance = resultSet.getInt("balance");
                String status = resultSet.getString("status");
                if (idData != accountId) {
                    System.out.println("id doesn't match");
                    return null;
                }
                return new Account(idData, customerIdData, type, balance, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void withdraw(int customerId, int accountId, double amount) {
        String sql = "BEGIN;" +
                "UPDATE account set balance = balance - ? WHERE id = ? AND status='approved';" +
                "INSERT INTO log (id, date, customerid, accountid, description, amount) VALUES (default, default, ?, ?, 'withdrawal', ?);" +
                "COMMIT;" +
                "END;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, accountId);
                preparedStatement.setInt(3, customerId);
                preparedStatement.setInt(4, accountId);
                preparedStatement.setDouble(5, amount);
                int count = preparedStatement.executeUpdate();
                if (count == 0) {
                    System.out.println("Withdrawal successful.");
                } else {
                    System.out.println("Something went wrong with withdrawal.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    @Override
    public void deposit(int customerId, int accountId, double amount) {
        String sql = "BEGIN;" +
                "UPDATE account set balance = balance + ? WHERE id = ? AND status='approved';" +
                "INSERT INTO log (id, date, customerid, accountid, description, amount) VALUES (default, default, ?, ?, 'deposit', ?);" +
                "COMMIT;" +
                "END;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, accountId);
                preparedStatement.setInt(3, customerId);
                preparedStatement.setInt(4, accountId);
                preparedStatement.setDouble(5, amount);
                int count = preparedStatement.executeUpdate();
                if (count == 0) {
                    System.out.println("Deposit successful.");
                } else {
                    System.out.println("Something went wrong with deposit.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    @Override
    public void postTransfer(int customerId, int id, int toid, double amount) {
        /*"Stored procedure in SQL"
        CREATE OR REPLACE PROCEDURE transfer (customerId INTEGER, id INTEGER, toid INTEGER, amo DECIMAL)
        LANGUAGE SQL
        AS $$
        UPDATE account SET balance = balance - amo WHERE id = id AND status ='approved';
        UPDATE account SET balance = balance + amo WHERE id = toid;
        UPDATE account SET status = 'incoming fund' WHERE id = toid;
        INSERT INTO log (id, date, customerid, accountid, description, amount) VALUES (default, default, customerId, id, 'transfer posted', amo);
        $$;
         */
        String sql = "CALL transfer(?::INTEGER, ?::INTEGER, ?::INTEGER, ?::DECIMAL);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setInt(2,id);
            preparedStatement.setInt(3,toid);
            preparedStatement.setDouble(4,amount);
            int count = preparedStatement.executeUpdate();
            if (count==0) {
                System.out.println("Transfer posted successfully.");
            }
            else {
                System.out.println("something went wrong with transfer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void acceptTransfer(int customerId, int id) {
        /*"Stored procedure in SQL"
        CREATE OR REPLACE PROCEDURE accept (customerId INTEGER, id INTEGER)
        LANGUAGE SQL
        AS $$
        UPDATE account SET status = 'approved' WHERE id = id;
        INSERT INTO log (id, date, customerid, accountid, description, amount) VALUES (default, default, customerId, id, 'fund accepted', default);
        $$;
         */
        String sql = "CALL accept(?::INTEGER, ?::INTEGER);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setInt(2,id);
            int count = preparedStatement.executeUpdate();
            if(count==0) {
                System.out.println("Accepted incoming fund");
            }
            else {
                System.out.println("Something went wrong.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initTables() {
        String sql = "CREATE TABLE customer(id SERIAL PRIMARY KEY, username VARCHAR(20) UNIQUE NOT NULL, password VARCHAR(20) NOT NULL);";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillTables() {
        String sql = "INSERT INTO customer (id, username, password) VALUES (default, 'cust1', 1);";
        sql += "INSERT INTO customer (id, username, password) VALUES (default, 'cust2', 2);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
