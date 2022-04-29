package org.example.entities;

import java.util.Date;

public class Ticket {
    private int id;
    private Date date;
    private int userId;
    private double amount;
    private String description;
    private String state;

    public Ticket(int id, Date date, int userId, double amount, String description, String state) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }
}
