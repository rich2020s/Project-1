package org.example.dao;


import org.example.ConnectionFactory;
import org.example.dataStructure.CustomArrayList;

import java.sql.*;

import org.example.entities.Tickets;

public class ManagerDaoImpl implements ManagerDao{
    Connection connection;

    public ManagerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }
    @Override
    public CustomArrayList<Tickets> viewAllPendingRequests() {
        String sql = "SELECT * FROM tickets WHERE state = ?";
        CustomArrayList<Tickets> tickets = new CustomArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "pending");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tickets ticket = new Tickets(rs.getInt("id"), rs.getDate("created_at"), rs.getInt("user_id"),
                        rs.getDouble("price"), rs.getString("description"), rs.getString("state"));
                tickets.add(ticket);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public CustomArrayList<Tickets> viewAllTickets() {
        String sql = "SELECT * FROM tickets where state != 'pending'";
        CustomArrayList<Tickets> tickets = new CustomArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tickets ticket = new Tickets(rs.getInt("id"), rs.getDate("date"), rs.getInt("user_id"),
                        rs.getDouble("price"), rs.getString("description"), rs.getString("state"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when selecting tickets");
        }
        return tickets;
    }

    @Override
    public void acceptRequest(int id) {
        String sql = "UPDATE tickets SET state = 'approved' WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            int success = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (success == 1) {
                System.out.println(" successful");
            } else {
                throw new SQLException("update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when updating tickets");
        }
    }

    @Override
    public void denyRequest(int id) {
        String sql = "UPDATE tickets SET state = 'rejected' WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            int success = preparedStatement.executeUpdate();
            if (success == 0) {
                throw new SQLException("update failed");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when updating tickets");
        }
    }

    @Override
    public void initTables () throws SQLException {
        String accountsQuery = "CREATE TABLE accounts (id SERIAL PRIMARY KEY, username VARCHAR(50), password VARCHAR (50)," +
                " user_type VARCHAR(1))";
        String ticketsQuery = "CREATE TABLE tickets (id SERIAL PRIMARY KEY, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, user_id integer references accounts(id), price decimal(10,2), description VARCHAR(100), state VARCHAR(50));";
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
    public void insertRequest(Tickets ticket) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Tickets getTicketsById(int id) {
        String sql = "SELECT * from tickets where id = ?";
        Tickets ticket = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            ticket = new Tickets(rs.getInt("id"), rs.getDate("created_at"), rs.getInt("user_id"),
                    rs.getDouble("price"), rs.getString("description"), rs.getString("state"));
//            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
