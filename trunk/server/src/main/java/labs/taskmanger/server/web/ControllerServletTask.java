package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.EAVCRDaoTask;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoAssignee;
import labs.taskmanger.common.service.JDBCDaoTask;
import labs.taskmanger.server.ejb.AssigneeMangerBeanLocal;
import labs.taskmanger.server.ejb.TaskManagerBeanLocal;

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

@WebServlet("/loadTask")
public class ControllerServletTask extends HttpServlet {

    @EJB
    TaskManagerBeanLocal bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //GenericDao taskDAO = new JDBCDaoTask();
        EAVCRDaoTask taskDAO = new EAVCRDaoTask();
        List<Entity> entities = taskDAO.readAll();
        List<Task> tasks = parseListEntityToListTask(entities);
        request.setAttribute("tasks", tasks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Task> tasks = new ArrayList<>();
        String action = request.getParameter("action");
        String nameTask = request.getParameter("nameTask");
        String descriptionTask = request.getParameter("descriptionTask");
        String deadlineTask = request.getParameter("deadlineTask");
        String priorityTask = request.getParameter("priorityTask");
        String statusTask = request.getParameter("statusTask");
        Task task = new TaskImpl(nameTask, descriptionTask, deadlineTask, priorityTask, statusTask);

        if ("Search".equals(action)) {

            tasks = bean.searchTaskOnJSP(task.getTaskName(), task.getStatus());
            request.setAttribute("tasks", tasks);
            request.setAttribute("taskName", nameTask);
            request.setAttribute("status", statusTask);
            RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
            dispatcher.forward(request, response);
        }

        if ("Add".equals(action)) {

            bean.addTaskFromJSP(task.getTaskName(), task.getDescription(), task.getDeadline(), task.getPriority(), task.getStatus());
            request.setAttribute("taskName", nameTask);
            request.setAttribute("description", descriptionTask);
            request.setAttribute("deadline", deadlineTask);
            request.setAttribute("priority", priorityTask);
            request.setAttribute("status", statusTask);
            doGet(request, response);
        }

        if ("Delete".equals(action)) {

            bean.deleteTaskFromJSP(task.getTaskName(), task.getDescription(), task.getDeadline(), task.getPriority(), task.getStatus());
            request.setAttribute("taskName", nameTask);
            request.setAttribute("description", descriptionTask);
            request.setAttribute("deadline", deadlineTask);
            request.setAttribute("priority", priorityTask);
            request.setAttribute("status", statusTask);
            doGet(request, response);
        }


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