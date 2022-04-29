package org.example;

import org.example.services.EmployeeService;
import org.example.services.ManagerService;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args ){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello welcome!");
        System.out.println("If you have an employee account, press 1 to login");
        System.out.println("If you would like to register, press 2");
        System.out.println("If you are a manager, press 3");

        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                int user_id = EmployeeService.loginEmployee();
                while(flag) {
                    System.out.println("option 1: Submit a ticket for reimbursement");
                    System.out.println("option 2: View your past approved tickets");
                    System.out.println("option 3: View your pending tickets");
                    System.out.println("option 4: View your tickets history");
                    System.out.println("option 5: Quit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            EmployeeService.applyReimbursement(user_id);
                            break;
                        case 2:
                            EmployeeService.viewPastTickets(user_id);
                            break;
                        case 3:
                            EmployeeService.viewPendingTickets(user_id);
                            break;
                        case 4:
                            EmployeeService.viewAllTickets(user_id);
                            break;
                        case 5:
                            flag = false;
                            break;
                        default:
                            System.out.println("Please enter 1 - 5");
                    }
                }
                break;
            case 2:
                EmployeeService.registerCustomer();
                break;
            case 3:
                ManagerService.loginEmp();
                while(flag) {
                    System.out.println("option 1: view customer's bank accounts");
                    System.out.println(("option 2: view log of all transactions"));
                    System.out.println("option 3: approve customer's account");
                    System.out.println("option 4: deny customer's account");
                    System.out.println("option 5: quit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            ManagerService.accountView();
                            break;
                        case 2:
                            ManagerService.logView();
                            break;
                        case 3:
                            ManagerService.accountApp();
                            break;
                        case 4:
                            ManagerService.accountReject();
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
