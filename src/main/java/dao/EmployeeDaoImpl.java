package dao;

import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import entities.Accounts;
import org.example.ConnectionFactory;
import entities.Tickets;

import java.sql.*;

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
                System.out.println("Employee login failed.");
                System.exit(0);
                return null;
            }

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
            Timestamp created_at = resultSet.getTimestamp("created_at");
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


}