package org.example.services;

import org.example.dao.DaoFactory;
import org.example.dao.ManagerDao;
import org.example.entities.Tickets;

import java.util.List;
import java.util.Scanner;

//public class ManagerService {

//    public static void loginEmp() {
//        System.out.println("Please enter your username: ");
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        System.out.println("Please enter password: ");
//        String password = scanner.nextLine();
//
//        ManagerDao empDao = DaoFactory.getEmpDao();
//        empDao.login(username, password);
//    }

//    public static void accountView() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter customer id to view accounts: ");
//        int accountId = scanner.nextInt();
//
//        ManagerDao empDao = DaoFactory.getEmpDao();
//        List<Account> accountList = empDao.viewAccount(accountId);
//        for(Account account : accountList) {
//            System.out.println(account.toString());
//        }
//    }
//
//    public static void logView() {
//        System.out.println("Here is the list of all transactions.");
//        ManagerDao empDao = DaoFactory.getEmpDao();
//        List<Tickets> logList = empDao.viewLog();
//        for(Tickets log : logList) {
//            System.out.println(log.toString());
//        }
//    }
//
//    public static void accountApp() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter account id you would like to approve: ");
//        int accountId = scanner.nextInt();
//        System.out.println("Enter customer id associated with this account: ");
//        int customerId = scanner.nextInt();
//        ManagerDao empDao = DaoFactory.getEmpDao();
//        empDao.appAccount(accountId, customerId);
//    }
//
//    public static void accountReject() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter account id you would like to deny: ");
//        int accountId = scanner.nextInt();
//        System.out.println("Enter customer id associated with this account: ");
//        int customerId = scanner.nextInt();
//        ManagerDao empDao = DaoFactory.getEmpDao();
//        empDao.rejAccount(accountId, customerId);
//    }
//}
