package org.example.services;

import org.example.dao.EmployeeDao;
import org.example.dao.DaoFactory;
import org.example.entities.Accounts;
import org.example.entities.Tickets;

import java.util.List;
import java.util.Scanner;


public class EmployeeService {

    public static void registerCustomer() {
        System.out.println("Please enter new username(must be unique): ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Please enter new password: ");
        String password = scanner.nextLine();

        Accounts account = new Accounts(username,password, "E");
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.register(account);
    }

    public static int loginEmployee() {
        System.out.println("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        int user_id = employeeDao.login(username, password);
        return user_id;
    }

    public static void applyReimbursement(int user_id) {
        System.out.println("Please enter amount for reimbursement: ");
        Scanner scanner = new Scanner(System.in);
        double price = scanner.nextDouble();
        System.out.println("Please enter the description: ");
        String description = scanner.nextLine();
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.apply(user_id, price, description);
    }

    public static void viewPastTickets(int user_id) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        List<Tickets> tickets = employeeDao.getPast(user_id);
        for (Tickets ticket : tickets){
            System.out.println(ticket);
        }
    }

    public static void viewPendingTickets(int user_id) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        List<Tickets> tickets = employeeDao.getPending(user_id);
        for (Tickets ticket : tickets){
            System.out.println(ticket);
        }
    }

    public static void viewAllTickets(int user_id) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        List<Tickets> tickets = employeeDao.getHistory(user_id);
        for (Tickets ticket : tickets){
            System.out.println(ticket);
        }
    }


}
