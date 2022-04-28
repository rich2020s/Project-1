package org.example.entities;

public class Account {
    private int id;
    private int customerId;
    private String type;
    private double balance;
    private String status;

    public Account(int id, int customerId, String type, double balance, String status) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.balance = balance;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customer id=" + customerId +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
