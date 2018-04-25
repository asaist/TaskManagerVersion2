package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;

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

@WebServlet("/loadTask")
public class ControllerServletTask extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericDao taskDAO = new JDBCDaoTask();
        List<Entity> entities = taskDAO.readAll();
        List<Task> tasks = parseListEntityToListTask(entities);
        request.setAttribute("tasks", tasks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Task task1 = new TaskImpl();
        String nameTask  = request.getParameter("nameTask");
        String status  = request.getParameter("post");

        GenericDao taskDAO = new JDBCDaoTask();
        List<Entity> entities = taskDAO.readAll();
        List<Task> tasks = parseListEntityToListTask(entities);

        for (Task task:tasks){
            if (task.getTaskName().equals(nameTask) && task.getStatus().equals(status)){
                task1.setId(task.getId());
                task1.setTaskName(task.getTaskName());
                task1.setDescription(task.getDescription());
                task1.setDeadline(task.getDeadline());
                task1.setPriority(task.getPriority());
                task1.setStatus(task.getStatus());

            }
        }

        List<Task> tasks1 = new ArrayList<>();
        tasks1.add(task1);

        request.setAttribute("tasks", tasks1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
        dispatcher.forward(request, response);


    }

    private List<Task> parseListEntityToListTask(List<Entity> entities) {
        Task task = new TaskImpl();
        List<Task> tasks = new ArrayList();
        for (Entity entity: entities){
            task = (Task) entity;
            tasks.add(task);

        }
        return tasks;
    }


}