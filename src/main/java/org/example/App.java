package org.example;

import org.example.services.CustomerService;
import org.example.services.EmpService;

import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello welcome to __ Bank!");
        System.out.println("If you have an account, press 1 to login");
        System.out.println("If you would like to register, press 2");
        System.out.println("If you are an employee, press 3");

        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                int customerId = CustomerService.loginCustomer();
                while(flag) {
                    System.out.println("option 1: Apply new bank account");
                    System.out.println("option 2: See balance");
                    System.out.println("option 3: Withdraw");
                    System.out.println("option 4: Deposit");
                    System.out.println("option 5: Post transfer");
                    System.out.println("option 6: Accept incoming fund");
                    System.out.println("option 7: Quit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            CustomerService.applyAccount(customerId);
                            break;
                        case 2:
                            CustomerService.viewBalance(customerId);
                            break;
                        case 3:
                            CustomerService.withdrawalAccount(customerId);
                            break;
                        case 4:
                            CustomerService.depositAccount(customerId);
                            break;
                        case 5:
                            CustomerService.transferAccount(customerId);
                            break;
                        case 6:
                            CustomerService.acceptTransfer(customerId);
                        case 7:
                            flag = false;
                            break;
                        default:
                            System.out.println("Please enter 1 - 7");
                    }
                }
                break;
            case 2:
                CustomerService.registerCustomer();
                break;
            case 3:
                EmpService.loginEmp();
                while(flag) {
                    System.out.println("option 1: view customer's bank accounts");
                    System.out.println(("option 2: view log of all transactions"));
                    System.out.println("option 3: approve customer's account");
                    System.out.println("option 4: deny customer's account");
                    System.out.println("option 5: quit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            EmpService.accountView();
                            break;
                        case 2:
                            EmpService.logView();
                            break;
                        case 3:
                            EmpService.accountApp();
                            break;
                        case 4:
                            EmpService.accountReject();
                            break;
                        case 5:
                            flag = false;
                            break;
                        default:
                            System.out.println("Please enter 1 - 5");
                    }
                }
                break;

            default:
                System.out.println("Please enter 1 - 3");
        }
    }
}
