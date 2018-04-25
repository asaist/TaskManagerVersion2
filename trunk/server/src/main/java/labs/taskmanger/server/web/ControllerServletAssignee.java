package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loadAssignee")
    public class ControllerServletAssignee extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericDao assigneeDAO = new JDBCDaoAssignee();
        List<Entity> entities = assigneeDAO.readAll();
        List<Assignee> assignees = parseListEntityToListAssignee(entities);
        request.setAttribute("assignees", assignees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allAssignee.jsp");
        dispatcher.forward(request, response);

        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Assignee assignee1 = new AssigneeImpl();
        String name  = request.getParameter("name");
        String lastName  = request.getParameter("lastName");

        GenericDao assigneeDAO = new JDBCDaoAssignee();
        List<Entity> entities = assigneeDAO.readAll();
        List<Assignee> assignees = parseListEntityToListAssignee(entities);

        for (Assignee assignee:assignees){
            if (assignee.getName().equals(name) && assignee.getLastName().equals(lastName)){
                assignee1.setId(assignee.getId());
                assignee1.setName(assignee.getName());
                assignee1.setLastName(assignee.getLastName());
                assignee1.setPost(assignee.getPost());
            }
        }

        List<Assignee> assignees1 = new ArrayList<>();
        assignees1.add(assignee1);

        request.setAttribute("assignees", assignees1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allAssignee.jsp");
        dispatcher.forward(request, response);


    }

    private List<Assignee> parseListEntityToListAssignee(List<Entity> entities) {
        Assignee assignee = new AssigneeImpl();
        List<Assignee> assignees = new ArrayList();
        for (Entity entity: entities){
            assignee = (Assignee) entity;
            assignees.add(assignee);

        }
        return assignees;
    }


}