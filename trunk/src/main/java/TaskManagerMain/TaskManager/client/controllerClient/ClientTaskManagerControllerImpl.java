package TaskManagerMain.TaskManager.client.controllerClient;

import TaskManagerMain.TaskManager.client.modelClient.ClientTaskManagerModel;
import TaskManagerMain.TaskManager.client.viewClient.ClientTaskManagerView;
import TaskManagerMain.TaskManager.client.viewClient.ClientTaskManagerViewImpl;
import TaskManagerMain.TaskManager.common.entity.*;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class ClientTaskManagerControllerImpl implements ClientTaskManagerController {
    ClientTaskManagerModel model;
    ClientTaskManagerView view;
    ClientTaskManagerController controller;


    public ClientTaskManagerControllerImpl(ClientTaskManagerModel model) {
        this.model = model;
        view = new ClientTaskManagerViewImpl(this, model);

    }

    //Task
    public void addTask(String taskName, String description, String deadline, String priority, String status, String subtask) {
        Task task = new TaskImpl();
        checkFieldstask(taskName, description, deadline, priority, status, subtask, task);
        model.addTask(task);
    }


    public void checkFieldstask(String taskName, String description, String deadline, String priority, String status, String subtask, Task task) {


        if (isCorrect(taskName)) {
            throw new RuntimeException("t_name is not correct");
        } else {

            task.setTaskName(taskName.trim());
        }
        if (isCorrect(description)) {
            throw new RuntimeException("description is not correct");
        } else {
            task.setDescription(description.trim());
        }
        if (isCorrectYear(String.valueOf(deadline))) {
            throw new RuntimeException("deadline is not correct");
        } else {
            task.setDeadline(deadline);
        }
        if (isCorrect(priority)) {
            throw new RuntimeException("priority is not correct");
        } else {
            task.setPriority(priority.trim());
        }
        if (isCorrect(status)) {
            throw new RuntimeException("status is not correct");
        } else {
            task.setStatus(status.trim());
        }
    }

    // }

    //Assaignee
    public void addAssignee(String name, String lastName, String post) {
        Assignee assignee = new AssigneeImpl();
        checkFields(name, lastName, post, assignee);
        model.addAssaignee(assignee);
        System.out.println(assignee.toString());
    }


    public void checkFields(String name, String lastName, String post, Assignee assignee) {

        if (isCorrect(name)) {
            throw new RuntimeException("name is empty");
        } else {
            assignee.setName(name.trim());
        }

        if (isCorrect(lastName)) {
            throw new RuntimeException("lastName is empty");
        } else {
            assignee.setLastName(lastName.trim());
        }

        if (isCorrect(post)) {
            throw new RuntimeException("post is empty");
        } else {
            assignee.setPost(post.trim());
        }
    }

    private boolean isCorrect(String field) {
        return field == null || field.isEmpty() || field.trim().isEmpty() || field.indexOf(";") != -1;
    }

    private boolean isCorrectYear(String field) {
        Calendar calendar = new GregorianCalendar();
        return field == null || field.isEmpty() || field.trim().isEmpty() || field.indexOf(";") != -1 || Integer.parseInt(field) > calendar.get(Calendar.YEAR) + 3;
    }

    private boolean isCorrectMonth(String field) {
        return field == null || field.isEmpty() || field.trim().isEmpty() || field.indexOf(";") != -1 || Integer.parseInt(field) < 1 || Integer.parseInt(field) > 12;
    }

    private boolean isCorrectDay(String field) {
        return field == null || field.isEmpty() || field.trim().isEmpty() || field.indexOf(";") != -1 || Integer.parseInt(field) < 1 || Integer.parseInt(field) > 31;
    }

    private boolean isCorrectHour(String field) {
        return field == null || field.isEmpty() || field.trim().isEmpty() || field.indexOf(";") != -1 || Integer.parseInt(field) < 0 || Integer.parseInt(field) > 23;
    }

    public void deleteTask(Task taskToRemove) {
        model.deleteTask(taskToRemove);
    }

    public void updateTask(Task taskToUpdate) {
        model.updateTask(taskToUpdate);
    }

    public Date stringToDateTask(String taskDeadline) {

            DateFormat format = new SimpleDateFormat("YYYY.MM.DD.HH");
        Date taskTime = null;
        try {
            taskTime = format.parse(taskDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return taskTime;
    }
}


