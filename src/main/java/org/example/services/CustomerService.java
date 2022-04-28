package org.example.services;

import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.example.dao.EmpDao;
import org.example.entities.Account;
import org.example.entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CustomerService {

    public static void registerCustomer() {
        System.out.println("Please enter new username(must be unique): ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Please enter new password: ");
        String password = scanner.nextLine();

        Customer customer = new Customer(username,password);
        CustomerDao cusDao = DaoFactory.getCustomerDao();
        cusDao.register(customer);
    }

    public static int loginCustomer() {
        System.out.println("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        CustomerDao cusDao = DaoFactory.getCustomerDao();
        int customerId = cusDao.login(username, password);
        return customerId;
    }

    public static void applyAccount(int customerId) {
        System.out.println("Please enter the account type (Checking/Saving): ");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        if (type.equals("Checking") || type.equals("Saving")) {
            System.out.println("Please enter the starting balance: ");
            double balance = scanner.nextDouble();
            CustomerDao cusDao = DaoFactory.getCustomerDao();
            cusDao.apply(customerId, type, balance);
        }
        else {
            System.out.println("Please enter valid account type.");
        }
    }

    public static void viewBalance(int customerId) {
        System.out.println("Please enter the account id: ");
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        CustomerDao cusDao = DaoFactory.getCustomerDao();
        Account account = cusDao.getBalance(accountId, customerId);
        System.out.println(account.toString());
    }

    public static void withdrawalAccount(int customerId) {
        System.out.println("Enter the account id: ");
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        System.out.println("Enter the amount you would like to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Cannot withdraw non positive amount.");
        }
        else {
            CustomerDao customerDao = DaoFactory.getCustomerDao();
            customerDao.withdraw(customerId, accountId, amount);
        }
    }

    public static void depositAccount(int customerId) {
        System.out.println("Enter the account id: ");
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        System.out.println("Enter the amount you would like to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <=0 ) {
            System.out.println("Cannot deposit non positive amount.");
        }
        else {
            CustomerDao customerDao = DaoFactory.getCustomerDao();
            customerDao.deposit(customerId, accountId, amount);
        }
    }

    public static void transferAccount(int customerId) {
        System.out.println("From which account would you like to send money: ");
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        System.out.println("To which account would you like to send money to: ");
        int toId = scanner.nextInt();
        System.out.println("Enter amount: ");
        int amount = scanner.nextInt();

        if (amount <=0) {
            System.out.println("Cannot transfer non positive amount");
        }
        else {
            CustomerDao customerDao = DaoFactory.getCustomerDao();
            customerDao.postTransfer(customerId, accountId, toId, amount);
        }
    }

    public static void acceptTransfer (int customerId) {
        System.out.println("Enter the account id: ");
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        CustomerDao customerDao = DaoFactory.getCustomerDao();
        customerDao.acceptTransfer(customerId,accountId);
    }

}
