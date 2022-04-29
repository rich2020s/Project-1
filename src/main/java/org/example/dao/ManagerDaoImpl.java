package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
