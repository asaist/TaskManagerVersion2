package labs.taskmanger.server.web;

import labs.taskmanger.common.entity.*;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.common.service.JDBCDaoTask;

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


        String nameTask  = request.getParameter("nameTask");
        String status  = request.getParameter("post");

//        SearchBeanForTask searchBeanForTask = new SearchBeanForTaskImpl();
//        searchBeanForTask.setTaskName(nameTask);
//        searchBeanForTask.setStatus(status);
//        List<Task> tasks = searchBeanForTask.searchTaskOnJSP();
//
//        request.setAttribute("tasks", tasks);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("allTask.jsp");
//        dispatcher.forward(request, response);


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