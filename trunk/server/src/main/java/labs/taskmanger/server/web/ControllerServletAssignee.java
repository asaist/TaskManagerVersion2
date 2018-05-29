package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.service.EAVCRDaoAssignee;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.server.ejb.AssigneeMangerBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loadAssignee")
    public class ControllerServletAssignee extends HttpServlet {

    @EJB
    AssigneeMangerBeanLocal bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //GenericDao assigneeDAO = new JDBCDaoAssignee();
        EAVCRDaoAssignee assigneeDAO = new EAVCRDaoAssignee();
        List<Entity> entities = assigneeDAO.readAll();
        List<Assignee> assignees = parseListEntityToListAssignee(entities);
        request.setAttribute("assignees", assignees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allAssignee.jsp");
        dispatcher.forward(request, response);

        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Assignee> assignees = new ArrayList<>();
        String action = request.getParameter("action");
        String name = request.getParameter("nameAssignee");
        String lastName = request.getParameter("lastNameAssignee");
        String post = request.getParameter("postAssignee");
        Assignee assignee = new AssigneeImpl(name, lastName,  post);

        if ("Search".equals(action)) {

            assignees = bean.searchAssigneeOnJSP(assignee.getName(), assignee.getLastName());
            request.setAttribute("assignees", assignees);
            request.setAttribute("nameSearch", name);
            request.setAttribute("lastNameSearch", lastName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("allAssignee.jsp");
            dispatcher.forward(request, response);
        }

        if ("Add".equals(action)) {

            bean.addAssigneeFromJSP(assignee.getName(), assignee.getLastName(), assignee.getPost());
            request.setAttribute("nameAdd", name);
            request.setAttribute("lastNameAdd", lastName);
            request.setAttribute("postAdd", post);
            doGet(request, response);
        }

        if ("Delete".equals(action)) {

            bean.deleteAssigneeFromJSP(assignee.getName(), assignee.getLastName(), assignee.getPost());
            request.setAttribute("nameDelete", name);
            request.setAttribute("lastNameDelete", lastName);
            request.setAttribute("postDelete", post);
            doGet(request, response);
        }







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