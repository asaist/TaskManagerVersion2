package server.controller;

import common.entity.*;
import server.model.TaskManagerModel;
import server.view.TaskManagerView;
import server.view.TaskManagerViewImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TaskManagerControllerImpl implements TaskManagerController {
    TaskManagerModel model;
    TaskManagerView view;
    TaskManagerController controller;


    public TaskManagerControllerImpl(TaskManagerModel model) {
        this.model = model;
        view = new TaskManagerViewImpl(this, model);

    }

    //Task
    public void addTask(String t_name, String description, String deadlineYear, String deadlineMonth, String deadlineDay, String deadlineHour, String priority, String status, String subtask) {
        Task task = new TaskImpl();
        checkFieldstask(t_name, description, deadlineYear, deadlineMonth, deadlineDay, deadlineHour, priority, status, subtask, task);
        model.addTask(task);
    }


    public void checkFieldstask(String t_name, String description, String deadlineYear, String deadlineMonth, String deadlineDay, String deadlineHour, String priority, String status, String subtask, Task task) {


        if (isCorrect(t_name)) {
            throw new RuntimeException("t_name is not correct");
        } else {

            task.setT_name(t_name.trim());
        }
        if (isCorrect(description)) {
            throw new RuntimeException("description is not correct");
        } else {
            task.setDescription(description.trim());
        }
        if (isCorrectYear(deadlineYear)) {
            throw new RuntimeException("Year is not correct");
        } else {
            task.setDeadlineYear(deadlineYear.trim());
        }
        if (isCorrectMonth(deadlineMonth)) {
            throw new RuntimeException("Month is not correct");
        } else {
            task.setDeadlineMonth(deadlineMonth.trim());
        }
        if (isCorrectDay(deadlineDay)) {
            throw new RuntimeException("Day is not correct");
        } else {
            task.setDeadlineDay(deadlineDay.trim());
        }
        if (isCorrectHour(deadlineHour)) {
            throw new RuntimeException("Hour is not correct");
        } else {
            task.setDeadlineHour(deadlineHour.trim());
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
        if (isCorrect(subtask)) {
            throw new RuntimeException("subtask is not correct");
        } else {
            task.setSubtask(subtask.trim());
        }
    }

    // }

    //Assaignee
    public void addAssignee(String name, String lastName, String post) {
        Assignee assignee = new AssigneeImpl(name, lastName , post);
        checkFields(name, lastName, post, assignee);
        model.addAssaignee(assignee);
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
            assignee.setLastname(lastName.trim());
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

    public void deleteTask(Task taskToRemove) throws IOException {
        model.deleteTask(taskToRemove);
    }

    public void updateTask(Task taskToUpdate) throws IOException {
        model.updateTask(taskToUpdate);
    }

    public List<Entity> isCorrectDate(List<Entity> entitys) {
        List<Entity> isCorrectTasks = new ArrayList<>();
        if (entitys == null) {
            System.out.println("Задач пока нет");
        } else {

            DateFormat format = new SimpleDateFormat("YYYY.MM.DD.HH");
            Date localDate = new Date();

            for (Entity entity : entitys) {
                Task task = (Task) entity;
                try {
                    Date taskTime = format.parse(task.getDeadlineYear() + "." + task.getDeadlineMonth() + "." + task.getDeadlineDay() + "." + task.getDeadlineHour());
                    if (taskTime.after(localDate)) {
                        isCorrectTasks.add(entity);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }
        return isCorrectTasks;
    }
}


