package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.AssigneeImpl;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.server.ejb.asssignee.SearchBeanForAssignee;
import labs.taskmanger.server.ejb.asssignee.SearchBeanForAssigneeImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exportAssignee")
public class ExportAssignee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String name  = request.getParameter("nameExport");
        String lastName  = request.getParameter("lastNameExport");
        String post = request.getParameter("postExport");


        SearchBeanForAssignee searchBeanForAssignee = new SearchBeanForAssigneeImpl();
        searchBeanForAssignee.setName(name);
        searchBeanForAssignee.setLastName(lastName);
        searchBeanForAssignee.setPost(post);
        searchBeanForAssignee.exportAssigneeToXML();


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