package org.example.dao;

import org.example.ConnectionFactory;
import org.example.entities.Accounts;
import org.example.entities.Tickets;

import java.sql.*;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void register(Accounts employee) {
        String sql = "INSERT INTO accounts (id, username, password, user_type) VALUES (default, ?, ?, 'E');";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Employee account added successfully!");
            } else {
                System.out.println("Something when wrong adding the new employee account.");
            }

        } catch (SQLException e) {
            ;
            e.printStackTrace();
        }

    }

    @Override
    public int login(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ? AND user_type = 'E';";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int user_id = resultSet.getInt("id");
                System.out.println("Login successful!");
                return user_id;
            } else {
                System.out.println("Employee login failed.");
                System.exit(0);
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public void apply(int user_id, double price, String description) {
        String sql = "INSERT INTO tickets (id, date, user_id, price, description, state) VALUES (default, default, ?, ?. ?. 'pending');";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, description);
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Reimbursement ticket submitted, please wait for approval.");
            } else {
                System.out.println("Something when wrong submitting the ticket.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Tickets getTicket(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            Date date = resultSet.getDate("date");
            int user_id = resultSet.getInt("user_id");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            String state = resultSet.getString("state");
            return new Tickets(id, date, user_id, price, description, state);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Tickets> getPast(int user_id) {
        List<Tickets> tickets = CustomArrayList<>(); // here I didn't import ArrayList yet because we need to make the custom one.
        // I'm only selecting past approved tickets. (should I include the rejected ones too..?
        String sql = "SELECT * FROM ticket WHERE user_id =? AND state = 'approved';";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tickets ticket = getTicket(resultSet);
                tickets.add(ticket);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<Tickets> getPending(int user_id) {
        List<Tickets> tickets = CustomArrayList<>(); // here I didn't import ArrayList yet because we need to make the custom one.
        String sql = "SELECT * FROM ticket WHERE user_id =? AND state = 'pending';";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tickets ticket = getTicket(resultSet);
                tickets.add(ticket);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<Tickets> getHistory(int user_id) {
        List<Tickets> tickets = CustomArrayList<>(); // here I didn't import ArrayList yet because we need to make the custom one.
        String sql = "SELECT * FROM ticket WHERE user_id =?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tickets ticket = getTicket(resultSet);
                tickets.add(ticket);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    // I still need to fill out initTables and fillTables before testing.
    @Override
    public void initTables() {

    }

    @Override
    public void fillTables() {

    }

}