package server.controller;

import common.entity.*;
import server.model.TaskManagerModel;
import server.view.TaskManagerView;
import server.view.TaskManagerViewImpl;

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
    public void addTask(String taskName, String description, Date deadline, String priority, String status, String subtask) {
        Task task = new TaskImpl(taskName, description, deadline, priority, status, subtask);
        checkFieldsTask(task);
        model.addTask(task);
    }

    public void deleteTask(Task taskToRemove) {
        checkFieldsTask(taskToRemove);
        model.deleteTask(taskToRemove);
    }

    public void updateTask(Task taskToUpdate) {
        checkFieldsTask(taskToUpdate);
        model.updateTask(taskToUpdate);
    }

    public void checkFieldsTask(Task task) {


        if (isCorrect(task.getTaskName())) {
            throw new RuntimeException("Task name is not correct");
        } else {

            task.setTaskName(task.getTaskName().trim());
        }
        if (isCorrect(task.getDescription())) {
            throw new RuntimeException("Description is not correct");
        } else {
            task.setDescription(task.getDescription().trim());
        }
        if (isCorrectYear(String.valueOf(task.getDeadline()))) {
            throw new RuntimeException("Year is not correct");
        } else {
            task.setDeadline(task.getDeadline());
        }

        if (isCorrect(task.getPriority())) {
            throw new RuntimeException("Priority is not correct");
        } else {
            task.setPriority(task.getPriority().trim());
        }
        if (isCorrect(task.getStatus())) {
            throw new RuntimeException("Status is not correct");
        } else {
            task.setStatus(task.getStatus().trim());
        }
        if (isCorrect(task.getSubtask())) {
            throw new RuntimeException("Subtask is not correct");
        } else {
            task.setSubtask(task.getSubtask().trim());
        }
    }

    // }

    //Assaignee
    public void addAssignee(String name, String lastName, String post) {
        Assignee assignee = new AssigneeImpl(name, lastName , post);
        checkFieldsAssignee(assignee);
        model.addAssaignee(assignee);
    }

    public void deleteAssignee(Assignee assigneeToRemove) {
        checkFieldsAssignee(assigneeToRemove);
        model.deleteAssignee(assigneeToRemove);
    }

    public void updateAssignee(Assignee assigneeToUpdate) {
        checkFieldsAssignee(assigneeToUpdate);
        model.updateAssignee(assigneeToUpdate);
    }


    public void checkFieldsAssignee(Assignee assignee) {

        if (isCorrect(assignee.getName())) {
            throw new RuntimeException("Name is not correct");
        } else {
            assignee.setName(assignee.getName().trim());
        }

        if (isCorrect(assignee.getLastName())) {
            throw new RuntimeException("Last name is not correct");
        } else {
            assignee.setLastName(assignee.getLastName().trim());
        }

        if (isCorrect(assignee.getPost())) {
            throw new RuntimeException("Post is not correct");
        } else {
            assignee.setPost(assignee.getPost().trim());
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

    public List<Entity> isCorrectDate(List<Entity> entitys) {
        List<Entity> isCorrectTasks = new ArrayList<>();
        if (entitys == null) {
            System.out.println("Задач пока нет");
        } else {

            DateFormat format = new SimpleDateFormat("YYYY.MM.DD.HH");
            Date localDate = new Date();

            for (Entity entity : entitys) {
                Task task = (Task) entity;
                Date taskTime = task.getDeadline();
                if (taskTime.after(localDate)) {
                    isCorrectTasks.add(entity);
                }

            }

        }
        return isCorrectTasks;
    }
}


