package dao;

import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import entities.Accounts;
import org.example.ConnectionFactory;
import entities.Tickets;

import java.sql.*;
import java.time.LocalDateTime;

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // catch (SQLException e) {
    }

    @Override
    public Accounts login(Accounts account) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ? AND user_type = 'E';";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String user_type = resultSet.getString("user_type");
                Accounts employee = new Accounts(id, username, password, user_type);
                System.out.println("Login successful!");
                return employee;
            } else {
                throw new SQLException("Employee login failed");}
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void apply(Tickets ticket) {
        String sql = "INSERT INTO tickets (id, created_at, user_id, price, description, state) VALUES (default, default, ?, ?, ?, 'pending');";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getUser_id());
            preparedStatement.setDouble(2, ticket.getPrice());
            preparedStatement.setString(3, ticket.getDescription());
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("Reimbursement ticket submitted, please wait for approval.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Tickets getTicket(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            LocalDateTime created_at = resultSet.getObject("created_at", LocalDateTime.class);
            int user_id = resultSet.getInt("user_id");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            String state = resultSet.getString("state");
            return new Tickets(id, created_at, user_id, price, description, state);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public CustomList<Tickets> getPast(Accounts account) {
        CustomList<Tickets> tickets = new CustomArrayList<>();
        String sql = "SELECT * FROM tickets WHERE user_id =? AND (state = 'approved' OR state = 'denied');";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getId());
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
    public CustomList<Tickets> getPending(Accounts account) {
        CustomList<Tickets> tickets = new CustomArrayList<>(); // here I didn't import ArrayList yet because we need to make the custom one.
        String sql = "SELECT * FROM tickets WHERE user_id =? AND state = 'pending';";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getId());
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

    public CustomList<Tickets> getHistory(Accounts account) {
        CustomList<Tickets> tickets = new CustomArrayList<>(); // here I didn't import ArrayList yet because we need to make the custom one.
        String sql = "SELECT * FROM tickets WHERE user_id =?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getId());
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
    public void initTables() {
        String sqlAccounts = "CREATE TABLE IF NOT EXISTS accounts (id SERIAL PRIMARY KEY, username VARCHAR(50) UNIQUE NOT NULL, password VARCHAR (50), user_type VARCHAR(1));";
        String sqlTickets = "CREATE TABLE IF NOT EXISTS tickets (id SERIAL PRIMARY KEY, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, user_id integer, price decimal(10,2) CHECK (price > 0), description VARCHAR(100), state VARCHAR(50));";
        try {
            Statement statementAccounts = connection.createStatement();
            statementAccounts.execute(sqlAccounts);
            Statement statementTickets = connection.createStatement();
            statementTickets.execute(sqlTickets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void fillTickets() {
        String sql = "INSERT INTO tickets (id, created_at, user_id, price, description, state) VALUES(default, default, 1, 3.14, 'last Friday', 'pending');";
        sql += "INSERT INTO tickets (id, created_at, user_id, price, description, state) VALUES(default, default, 1, 3.14, 'last Friday', 'approved');";
        sql += "INSERT INTO tickets (id, created_at, user_id, price, description, state) VALUES(default, default, 1, 3.14, 'last Friday', 'denied');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTables() {
        String sqla = "DROP TABLE IF EXISTS accounts;";
        String sqlt = "DROP TABLE IF EXISTS tickets;";
        try {
            Statement statementAccounts = connection.createStatement();
            statementAccounts.execute(sqla);
            Statement statementTickets = connection.createStatement();
            statementTickets.execute(sqlt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
