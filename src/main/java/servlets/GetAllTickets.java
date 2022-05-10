package servlets;

import dao.DaoFactory;
import dao.ManagerDao;
import dataStructure.CustomArrayList;
import entities.Tickets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GetAllTickets extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        CustomArrayList<Tickets> tickets = managerDao.viewAllTickets();
        for (int i = 0; i < tickets.size(); i++) {
            Tickets ticket = tickets.get(i);
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String date = ticket.getCreated_at().format(dateTimeFormatter);
            resp.getWriter().print("Ticket id: " + ticket.getId() + ", ");
            resp.getWriter().print("user_id: " + ticket.getUser_id() + ", ");
            resp.getWriter().print("price: $" + ticket.getPrice() + ", ");
            resp.getWriter().print("description: " + ticket.getDescription() + ", ");
            resp.getWriter().print("state: " + ticket.getState() + ", ");
            resp.getWriter().print("date: " + ticket.getCreated_at() + ".\n");
        }
        resp.setStatus(200);
    }
}
