package dao;

import org.example.ConnectionFactory;
import entities.Tickets;
import java.sql.*;
import dataStructure.CustomArrayList;
import java.time.LocalDateTime;

public class ManagerDaoImpl implements ManagerDao {
    Connection connection;
    // When we instantiate, we get this connection.
    public ManagerDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public CustomArrayList<Tickets> viewAllPendingTickets() {
        String sql = "SELECT * FROM tickets WHERE state = ?";
        CustomArrayList<Tickets> tickets = new CustomArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "pending");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tickets ticket = new Tickets(rs.getInt("id"), rs.getObject("created_at", LocalDateTime.class), rs.getInt("user_id"),
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
        String sql = "SELECT * FROM tickets";
        CustomArrayList<Tickets> tickets = new CustomArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tickets ticket = new Tickets(rs.getInt("id"), rs.getObject("created_at", LocalDateTime.class), rs.getInt("user_id"),
                        rs.getDouble("price"), rs.getString("description"), rs.getString("state"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when selecting tickets");
        }
        return tickets;
    }

    @Override
    public boolean acceptTicket(int id) {
        String sql = "UPDATE tickets SET state = 'approved' WHERE id = ? and state = 'pending'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            int success = preparedStatement.executeUpdate();
            if (success == 1) {
                System.out.println("Ticket accepted.");
                return true;
            } else {
                throw new SQLException("update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when updating tickets");
        }
        return false;
    }
    @Override
    public boolean denyTicket(int id) {
        String sql = "UPDATE tickets SET state = 'denied' WHERE id = ? and state = 'pending'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            int success = preparedStatement.executeUpdate();
            if (success == 1) return true;
            else throw new SQLException("update failed");
        } catch (SQLException e) {
            System.out.println("Something went wrong when updating tickets");
        }
        return false;
    }
    @Override
    public void initTables () throws SQLException {
        String accountsQuery = "DROP TABLE accounts if exists; CREATE TABLE accounts (id SERIAL PRIMARY KEY, username VARCHAR(50) unique, password VARCHAR (50)," + " user_type VARCHAR(1))";
        String ticketsQuery = "DROP TABLE tickets if exists; CREATE TABLE tickets (id SERIAL PRIMARY KEY, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, user_id integer references accounts(id), price decimal(10,2), description VARCHAR(100), state VARCHAR(50));";
        try {
            connection.setAutoCommit(false);
            PreparedStatement prepareAccountsTable = connection.prepareStatement(accountsQuery);
            PreparedStatement prepareTicketsTable = connection.prepareStatement(ticketsQuery);
            prepareAccountsTable.execute();
            prepareTicketsTable.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Transaction is being rollback.");
        }
        connection.setAutoCommit(true);
    }
    @Override
    public void insertRequest(Tickets ticket) {
        String sql = "INSERT INTO tickets (id, created_at, user_id, price, description, state) VALUES(default, default, ?, ?, ?, 'pending')";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ticket.getUser_id());
            preparedStatement.setDouble(2, ticket.getPrice());
            preparedStatement.setString(3, ticket.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Tickets getTicketsById(int id) {
        String sql = "SELECT * from tickets WHERE id = ?";
        Tickets ticket = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) throw new SQLException("Fail to get ticket.");
            else {
                ticket = new Tickets(rs.getInt("id"), rs.getObject("created_at", LocalDateTime.class), rs.getInt("user_id"),
                    rs.getDouble("price"), rs.getString("description"), rs.getString("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }
    @Override
    public void insertFakeEmployee() {
        String sql = "INSERT INTO accounts(username, password, user_type) VALUES('test', 'test', 'E')";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int success = preparedStatement.executeUpdate();
            if (success == 0) throw new SQLException("Fail to insert employee.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        String sql = "drop table accounts if exists; drop table tickets if exists;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
