package org.example;


import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import entities.Accounts;
import entities.Tickets;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        Accounts emp = new Accounts(9,"emp1", "1","E");
        System.out.println(emp.getId());
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        CustomList<Tickets> test = employeeDao.getHistory(emp);
        System.out.println(test.print());
    }
}
