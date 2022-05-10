package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dataStructure.CustomArrayList;
import dataStructure.CustomList;
import entities.Accounts;
import entities.CurrentUser;
import entities.Tickets;

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
                CustomArrayList<Tickets> pastTickets = employeeDao.getPast(currentUser.getCurrentUser());
                for (int i = 0; i < pastTickets.size(); i++) {
                    Tickets ticket = pastTickets.get(i);
                    resp.getWriter().print("Ticket id: " + ticket.getId() + ", ");
                    resp.getWriter().print("user_id: " + ticket.getUser_id() + ", ");
                    resp.getWriter().print("price: $" + ticket.getPrice() + ", ");
                    resp.getWriter().print("description: " + ticket.getDescription() + ", ");
                    resp.getWriter().print("state: " + ticket.getState() + ", ");
                    resp.getWriter().print("date: " + ticket.getCreated_at() + ".\n");
                }
                resp.setStatus(200);
            } catch (IOException e) {
                resp.setStatus(500);
                System.out.println(e.getLocalizedMessage());
            }
        }

        //getting pending tickets
        else if (req.getParameter("tickets").equals("pending")){
            try {
                CustomArrayList<Tickets> pendingTickets = employeeDao.getPending(currentUser.getCurrentUser());
                for (int i = 0; i < pendingTickets.size(); i++) {
                    Tickets ticket = pendingTickets.get(i);
                    resp.getWriter().print("Ticket id: " + ticket.getId() + ", ");
                    resp.getWriter().print("user_id: " + ticket.getUser_id() + ", ");
                    resp.getWriter().print("price: $" + ticket.getPrice() + ", ");
                    resp.getWriter().print("description: " + ticket.getDescription() + ", ");
                    resp.getWriter().print("state: " + ticket.getState() + ", ");
                    resp.getWriter().print("date: " + ticket.getCreated_at() + ".\n");
                }
                resp.setStatus(200);
            } catch (IOException e) {
                resp.setStatus(500);
                System.out.println(e.getLocalizedMessage());
            }
        }

        //getting all ticket history
        else if (req.getParameter("tickets").equals("all")){
            try {
                CustomArrayList<Tickets> allTickets = employeeDao.getHistory(currentUser.getCurrentUser());
                for (int i = 0; i < allTickets.size(); i++) {
                    Tickets ticket = allTickets.get(i);
                    resp.getWriter().print("Ticket id: " + ticket.getId() + ", ");
                    resp.getWriter().print("user_id: " + ticket.getUser_id() + ", ");
                    resp.getWriter().print("price: $" + ticket.getPrice() + ", ");
                    resp.getWriter().print("description: " + ticket.getDescription() + ", ");
                    resp.getWriter().print("state: " + ticket.getState() + ", ");
                    resp.getWriter().print("date: " + ticket.getCreated_at() + ".\n");
                }
                resp.setStatus(200);
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


