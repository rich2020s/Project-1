package org.example.entities;

import java.util.Date;

public class Log {
    private int id;
    private Date date;
    private int customerId;
    private int accountId;
    private String description;
    private double amount;

    public Log(int id, Date date, int customerId, int accountId, String description, double amount) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.accountId = accountId;
        this.description = description;
        this.amount = amount;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", date=" + date +
                ", customerId=" + customerId +
                ", accountId=" + accountId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
