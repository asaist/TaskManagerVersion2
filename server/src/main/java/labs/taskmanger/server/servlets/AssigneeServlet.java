package labs.taskmanger.server.servlets;

import labs.taskmanger.server.domain.Assignee;
import labs.taskmanger.server.ejb.service.AssigneesDAO;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AssigneeServlet", urlPatterns = "/AssigneeServlet")
public class AssigneeServlet extends HttpServlet {

    @EJB
    private AssigneesDAO assigneesDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String assigneeIdStr = request.getParameter("assigneeIdStr");
        int assigneeId = Integer.parseInt(assigneeIdStr);
        String assigneeName = request.getParameter("assigneeName");
        String assigneeLastName = request.getParameter("assigneeLastName");
        String assigneePost = request.getParameter("assigneePost");

        Assignee assignee = new Assignee(assigneeId, assigneeName, assigneeLastName, assigneePost);

        if ("Add".equalsIgnoreCase(action)){
            assigneesDAO.addAssignee(assignee);
        } else if ("Edit".equalsIgnoreCase(action)){
            assigneesDAO.editAssignee(assignee);
        } else if ("Delete".equalsIgnoreCase(action)){
            assigneesDAO.deleteAssignee(assigneeId);
        } else if ("Search".equalsIgnoreCase(action)){
            assignee = assigneesDAO.getAssignee(assigneeId);
        }

        request.setAttribute("assignee", assignee);
        request.setAttribute("allAssignee", assigneesDAO.getAllAssignee());

        request.getRequestDispatcher("assigneeinfo.jsp").forward(request, response);


    }
}