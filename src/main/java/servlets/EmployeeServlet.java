package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeDaoImpl;
import org.example.dataStructure.CustomList;
import org.example.entities.Accounts;
import org.example.entities.CurrentUser;
import org.example.entities.Tickets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    CurrentUser currentUser = new CurrentUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //getting past tickets
        if (req.getParameter("tickets").equals("past")){
            try {
                CustomList<Tickets> pastTickets = employeeDao.getPast(currentUser.getCurrentUser());
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(pastTickets); // map the output to json
                resp.setStatus(202);
                resp.getWriter().print(json);
            } catch (IOException e) {
                resp.setStatus(500);
                System.out.println(e.getLocalizedMessage());
            }
        }

        //getting pending tickets
        else if (req.getParameter("tickets").equals("pending")){
            try {
                CustomList<Tickets> pendingTickets = employeeDao.getPending(currentUser.getCurrentUser());
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(pendingTickets); // map the output to json
                resp.setStatus(202);
                resp.getWriter().print(json);
            } catch (IOException e) {
                resp.setStatus(500);
                System.out.println(e.getLocalizedMessage());
            }
        }

        //getting all ticket history
        else if (req.getParameter("tickets").equals("all")){
            try {
                CustomList<Tickets> allTickets = employeeDao.getHistory(currentUser.getCurrentUser());
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(allTickets); // map the output to json
                resp.setStatus(202);
                resp.getWriter().print(json);
            } catch (IOException e) {
                resp.setStatus(500);
                System.out.println(e.getLocalizedMessage());
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("apply")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Tickets payload = mapper.readValue(req.getInputStream(), Tickets.class);
                employeeDao.apply(payload);
                resp.setStatus(201);
                resp.getWriter().print("applied to new ticket");
            } catch (IOException e) {
                resp.setStatus(500);
                System.out.println(e.getLocalizedMessage());
            }
        }

        else {
            if (req.getParameter("action").equals("register")) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Accounts payload = mapper.readValue(req.getInputStream(), Accounts.class);
                    employeeDao.register(payload);
                    resp.setStatus(201);
                    resp.getWriter().print("new employee added");
                } catch(IOException e) {
                    resp.setStatus(500);
                    System.out.println(e.getLocalizedMessage());
                }
            }

            else if (req.getParameter("action").equals("login")){
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        Accounts payload = mapper.readValue(req.getInputStream(), Accounts.class);
                        Accounts employee = employeeDao.login(payload);
                        currentUser.setCurrentUser(employee);
                        resp.setStatus(202);
                        resp.getWriter().print("login successful");
                    } catch (IOException e) {
                        resp.setStatus(500);
                        System.out.println(e.getLocalizedMessage());
                    }
                }
        }
        }
    }


