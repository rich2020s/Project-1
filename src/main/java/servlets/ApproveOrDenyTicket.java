package servlets;

import dao.DaoFactory;
import dao.ManagerDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApproveOrDenyTicket extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        System.out.println(id);
        System.out.println(action);
        if (action == null || id == null) {
            resp.setStatus(400);
            resp.getWriter().print("Invalid parameter.");
            return;
        }
        ManagerDao managerDao = DaoFactory.getManagerDao();
        if (action.equals("approved")) {
            if (managerDao.acceptTicket(Integer.parseInt(id))) {
                resp.setStatus(201);
                resp.getWriter().print("Ticket is approved.");
                return;
            }
        } else if(action.equals("denied")) {
            if (managerDao.denyTicket(Integer.parseInt(id))){
                resp.setStatus(201);
                resp.getWriter().print("Ticket is denied.");
                return;
            }
        } else {
            resp.setStatus(400);
            resp.getWriter().print("Invalid parameter or id.");
            return;
        }
        resp.setStatus(400);
        resp.getWriter().print("Invalid parameter or id.");
    }
}
