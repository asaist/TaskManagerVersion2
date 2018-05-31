package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.*;
import labs.taskmanger.server.ejb.AssigneeMangerBeanLocal;
import labs.taskmanger.server.ejb.TaskManagerBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loadTask")
public class ControllerServletTask extends HttpServlet {

    //GenericDao<Assignee> assigneeDao = new JDBCDaoAssignee();
    EAVCRDaoTask taskDao = new EAVCRDaoTask();
    List<Task> tasks = new ArrayList<>();

    @EJB
    TaskManagerBeanLocal bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bean.inicialize(taskDao);
        tasks = bean.parseListEntityToListTask(taskDao.readAll());
        request.setAttribute("tasks", tasks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            request.setAttribute("tasksSearch", tasks);
            request.setAttribute("taskNameSearch", nameTask);
            request.setAttribute("statusSearch", statusTask);
            RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
            dispatcher.forward(request, response);
        }

        if ("Add".equals(action)) {

            bean.addTaskFromJSP(task.getTaskName(), task.getDescription(), task.getDeadline(), task.getPriority(), task.getStatus());
            request.setAttribute("taskNameAdd", nameTask);
            request.setAttribute("descriptionAdd", descriptionTask);
            request.setAttribute("deadlineAdd", deadlineTask);
            request.setAttribute("priorityAdd", priorityTask);
            request.setAttribute("statusAdd", statusTask);
            doGet(request, response);
        }

        if ("Delete".equals(action)) {

            bean.deleteTaskFromJSP(task.getTaskName(), task.getDescription(), task.getDeadline(), task.getPriority(), task.getStatus());
            request.setAttribute("taskNameDelete", nameTask);
            request.setAttribute("descriptionDelete", descriptionTask);
            request.setAttribute("deadlineDelete", deadlineTask);
            request.setAttribute("priorityDelete", priorityTask);
            request.setAttribute("statusDelete", statusTask);
            doGet(request, response);
        }

        if ("Export".equals(action)) {

            try {
                bean.taskToXML(tasks);
            } catch (ParserConfigurationException e) {

            } catch (TransformerException e) {

            }
            doGet(request, response);

        }

        if ("Load All Task".equals(action)) {

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