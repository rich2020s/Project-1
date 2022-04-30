package org.example.entities;

import java.util.Date;

public class Tickets {
    // id: serial primary key
    // date: TIMESTAMPTZ DEFAULT NOW()
    // user_id: foreign key from accounts table
    // price: DECIMAL (15,2)

    private int id;
    private Date date;
    private int user_id;
    private double price;
    private String description;
    private String state;

    public Tickets(int id, Date date, int user_id, double price, String description, String state) {
        this.id = id;
        this.date = date;
        this.user_id = user_id;
        this.price = price;
        this.description = description;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", date=" + date +
                ", user_id=" + user_id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
